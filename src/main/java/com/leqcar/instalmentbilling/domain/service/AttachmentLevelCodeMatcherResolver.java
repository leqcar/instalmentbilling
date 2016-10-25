package com.leqcar.instalmentbilling.domain.service;

import java.util.Map;
import java.util.function.Function;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevel;
import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevelCodeMatcher;

/**
 * Created by jongtenerife on 24/10/2016.
 */
public class AttachmentLevelCodeMatcherResolver implements Function<AttachmentLevel, AttachmentLevelCodeMatcher> {

    private Map<AttachmentLevel, AttachmentLevelCodeMatcher> attachmentLevelCodeMatchers;


	public AttachmentLevelCodeMatcherResolver(
			Map<AttachmentLevel, AttachmentLevelCodeMatcher> attachmentLevelCodeMatchers) {
		super();
		this.attachmentLevelCodeMatchers = attachmentLevelCodeMatchers;
	}


	@Override
    public AttachmentLevelCodeMatcher apply(AttachmentLevel attachmentLevel) {
		return attachmentLevelCodeMatchers.get(attachmentLevel);
    }
}
