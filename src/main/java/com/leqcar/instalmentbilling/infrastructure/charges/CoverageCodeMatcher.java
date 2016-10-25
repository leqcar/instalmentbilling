package com.leqcar.instalmentbilling.infrastructure.charges;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevelCodeMatcher;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.product.Premium;

/**
 * Created by jongtenerife on 24/10/2016.
 */
public class CoverageCodeMatcher implements AttachmentLevelCodeMatcher {
	
	List<BiPredicate<ChargesRule, Premium>> coverageLevelPredicates = initializeCoverageLevelPredicates();
		
    @Override
    public boolean matches(ChargesRule chargesRule, Premium premium) {
        return coverageLevelPredicates.stream()
        		.allMatch(e -> e.test(chargesRule, premium));
    }
    
    private List<BiPredicate<ChargesRule, Premium>> initializeCoverageLevelPredicates() {
    	
    	List<BiPredicate<ChargesRule, Premium>> coverageLevelPredicates = Arrays.asList(
    			(c, p) -> c.getCoverageCode().equalsIgnoreCase(p.getCoverageCode())
    			, (c, p) -> c.getRiskCode().equalsIgnoreCase(p.getRiskCode())
    			, (c, p) -> c.getSectionCode().equalsIgnoreCase(p.getSectionCode())
    			, (c, p) -> c.getProductCode().equalsIgnoreCase(p.getProductId().getCode())
    			, (c, p) -> c.getStateCode().equals(p.getStateCode())
    			, (c, p) -> c.getLocationNumber().equals(p.getLocationNo())
    			);

    	return coverageLevelPredicates;
    }  
}
