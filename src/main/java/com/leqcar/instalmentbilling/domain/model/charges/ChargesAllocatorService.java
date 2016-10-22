package com.leqcar.instalmentbilling.domain.model.charges;

import com.leqcar.instalmentbilling.domain.model.policy.Policy;

public interface ChargesAllocatorService {
	
	public void allocateCharges(Policy aPolicy, ChargesRule chargeRule);
}
