package com.leqcar.instalmentbilling.domain.model.policy;

import com.leqcar.instalmentbilling.domain.model.quote.WipId;

public interface PolicyRepository {

	Policy findByWip(Policy policy);

	Policy findPolicyInformation(WipId quoteId);

}
