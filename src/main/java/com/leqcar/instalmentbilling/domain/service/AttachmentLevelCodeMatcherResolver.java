package com.leqcar.instalmentbilling.domain.service;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevel;
import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevelCodeMatcher;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.product.Premium;
import com.leqcar.instalmentbilling.infrastructure.charges.CoverageCodeMatcher;
import com.leqcar.instalmentbilling.infrastructure.charges.RiskLevelCodeMatcher;
import com.leqcar.instalmentbilling.infrastructure.charges.SectionLevelCodeMatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Created by jongtenerife on 24/10/2016.
 */
public class AttachmentLevelCodeMatcherResolver implements Function<AttachmentLevel, AttachmentLevelCodeMatcher> {

    private AttachmentLevelCodeMatcher attachmentLevelCodeMatcher;
    private Map<AttachmentLevel, BiPredicate<ChargesRule, Premium>> mapPredicates;

    @Override
    public AttachmentLevelCodeMatcher apply(AttachmentLevel attachmentLevel) {
        mapPredicates = new HashMap<>();
        mapPredicates.put(AttachmentLevel.COVERAGE_LEVEL, (a, b) -> a.getCoverage().equals(b.getCoverageCode()));
        mapPredicates.put(AttachmentLevel.RISK_LEVEL, (a, b) -> a.getRiskCode().equals(b.getRiskCode()));
        mapPredicates.put(AttachmentLevel.SECTION_LEVEL, (a, b) -> a.getSectionCode().equals(b.getSectionCode()));
        mapPredicates.put(AttachmentLevel.PRODUCT_LEVEL, (a, b) -> a.getProductCode().equals(b.getProductId().getCode()));

        if (AttachmentLevel.SECTION_LEVEL.sameValueAs(attachmentLevel)) {
            attachmentLevelCodeMatcher = new SectionLevelCodeMatcher(mapPredicates);
        } else if (AttachmentLevel.RISK_LEVEL.sameValueAs(attachmentLevel)) {
            attachmentLevelCodeMatcher = new RiskLevelCodeMatcher(mapPredicates);
        } else if (AttachmentLevel.COVERAGE_LEVEL.sameValueAs(attachmentLevel)) {
            attachmentLevelCodeMatcher = new CoverageCodeMatcher(mapPredicates);
        }
        return attachmentLevelCodeMatcher;
    }
}
