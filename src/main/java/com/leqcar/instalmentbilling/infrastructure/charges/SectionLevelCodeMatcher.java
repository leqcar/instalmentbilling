package com.leqcar.instalmentbilling.infrastructure.charges;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevel;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.product.Premium;
import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevelCodeMatcher;

import java.util.Map;
import java.util.function.BiPredicate;

/**
 * Created by jongtenerife on 24/10/2016.
 */
public class SectionLevelCodeMatcher implements AttachmentLevelCodeMatcher {

    private Map<AttachmentLevel, BiPredicate<ChargesRule, Premium>> mapPredicates;
    public SectionLevelCodeMatcher(Map<AttachmentLevel, BiPredicate<ChargesRule, Premium>> mapPredicates) {
        this.mapPredicates = mapPredicates;
    }

    @Override
    public boolean matches(ChargesRule chargesRule, Premium premium) {
        return mapPredicates.get(AttachmentLevel.SECTION_LEVEL)
                .and(mapPredicates.get(AttachmentLevel.PRODUCT_LEVEL))
                .and((c, p) -> chargesRule.getStateCode().equals(premium.getStateCode()))
                .and((c, p) -> chargesRule.getLocationNumber().equals(premium.getLocationNo()))
                .test(chargesRule, premium);
    }
}
