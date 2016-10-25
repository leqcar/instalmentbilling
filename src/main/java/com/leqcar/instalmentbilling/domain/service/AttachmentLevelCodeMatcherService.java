package com.leqcar.instalmentbilling.domain.service;

import java.util.HashMap;
import java.util.Map;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevel;
import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevelCodeMatcher;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.product.Premium;
import com.leqcar.instalmentbilling.infrastructure.charges.CoverageCodeMatcher;
import com.leqcar.instalmentbilling.infrastructure.charges.ProductLevelCodeMatcher;
import com.leqcar.instalmentbilling.infrastructure.charges.RiskLevelCodeMatcher;
import com.leqcar.instalmentbilling.infrastructure.charges.SectionLevelCodeMatcher;

/**
 * Created by jongtenerife on 22/10/2016.
 */
public class AttachmentLevelCodeMatcherService {

    private AttachmentLevelCodeMatcher attachmentLevelCodeMatcher;
    private Map<AttachmentLevel, AttachmentLevelCodeMatcher> attachmentLevelCodeMatchers = createAttachmentLevelCodeMatchers();

    //TODO: Inject this as a bean 
	private Map<AttachmentLevel, AttachmentLevelCodeMatcher> createAttachmentLevelCodeMatchers() {
		Map<AttachmentLevel, AttachmentLevelCodeMatcher> attachmentLevelCodeMatchers = new HashMap<>();
		attachmentLevelCodeMatchers.put(AttachmentLevel.PRODUCT_LEVEL, new ProductLevelCodeMatcher());
		attachmentLevelCodeMatchers.put(AttachmentLevel.SECTION_LEVEL, new SectionLevelCodeMatcher());
		attachmentLevelCodeMatchers.put(AttachmentLevel.RISK_LEVEL, new RiskLevelCodeMatcher());
		attachmentLevelCodeMatchers.put(AttachmentLevel.COVERAGE_LEVEL, new CoverageCodeMatcher());
		return attachmentLevelCodeMatchers;
	}


	public boolean verifyAttachLevelCodeMatchOf(ChargesRule chargesRule, Premium premium) {
        AttachmentLevelCodeMatcherResolver resolver = new AttachmentLevelCodeMatcherResolver(attachmentLevelCodeMatchers);
        attachmentLevelCodeMatcher = resolver.apply(chargesRule.getAttachmentLevel());
        return attachmentLevelCodeMatcher.matches(chargesRule, premium);
    }
}
