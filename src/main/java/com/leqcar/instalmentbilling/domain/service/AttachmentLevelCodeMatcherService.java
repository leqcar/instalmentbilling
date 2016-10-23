package com.leqcar.instalmentbilling.domain.service;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevelCodeMatcher;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.product.Premium;

/**
 * Created by jongtenerife on 22/10/2016.
 */
public class AttachmentLevelCodeMatcherService {

    private AttachmentLevelCodeMatcher attachmentLevelCodeMatcher;

    public boolean verifyAttachLevelCodeMatchOf(ChargesRule chargesRule, Premium premium) {
        AttachmentLevelCodeMatcherResolver resolver = new AttachmentLevelCodeMatcherResolver();
        attachmentLevelCodeMatcher = resolver.apply(chargesRule.getAttachmentLevel());
        return attachmentLevelCodeMatcher.matches(chargesRule, premium);
    }
}
