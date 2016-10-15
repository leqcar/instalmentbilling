package com.leqcar.instalmentbilling.domain.model.policy;

import com.leqcar.instalmentbilling.domain.model.quote.QuoteId;

public interface PolicyRepository {

	Policy findByWip(Policy policy);

	Policy findPolicyInformation(QuoteId quoteId);

}
