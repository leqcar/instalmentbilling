package com.leqcar.instalmentbilling.domain.model.quote;

import com.leqcar.instalmentbilling.domain.model.policy.Policy;

public class Quote {

	private QuoteId quoteId;

	private Policy policy;

	public Quote(QuoteId quoteId, Policy policy) {
		super();
		this.quoteId = quoteId;
		this.policy = policy;
	}
}
