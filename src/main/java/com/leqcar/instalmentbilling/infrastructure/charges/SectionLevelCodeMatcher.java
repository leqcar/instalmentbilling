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
public class SectionLevelCodeMatcher implements AttachmentLevelCodeMatcher {

	private List<BiPredicate<ChargesRule, Premium>> sectionLevelPredicates = initializeSectionLevelPredicates();
	
    @Override
    public boolean matches(ChargesRule chargesRule, Premium premium) {
        return sectionLevelPredicates.stream()
        		.allMatch(e -> e.test(chargesRule, premium));
        
    }
    
    private List<BiPredicate<ChargesRule, Premium>> initializeSectionLevelPredicates() {    	
    	List<BiPredicate<ChargesRule, Premium>> sectionLevelPredicates = Arrays.asList(
    			(c, p) -> c.getSectionCode().equalsIgnoreCase(p.getSectionCode())
    			, (c, p) -> c.getProductCode().equalsIgnoreCase(p.getProductId().getCode())
    			, (c, p) -> c.getStateCode().equals(p.getStateCode())
    			, (c, p) -> c.getLocationNumber().equals(p.getLocationNo()));
    	
    	return sectionLevelPredicates;
    }         
}
