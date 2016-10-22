package com.leqcar.instalmentbilling.domain.model.charges;

import java.util.Arrays;
import java.util.List;

import com.leqcar.instalmentbilling.domain.model.product.Premium;

public class SectionLevelMatcher implements AttachmentLevelMatcher {

	@Override
	public boolean test(ChargesRule chargesRule, Premium premium) {
		
		AttachmentLevel chargesAttachmentLevel= chargesRule.getAttachmentLevel();
		List<AttachmentLevel> attachmentLevels = Arrays.asList(AttachmentLevel.values());
		
		return true;
	}

}
