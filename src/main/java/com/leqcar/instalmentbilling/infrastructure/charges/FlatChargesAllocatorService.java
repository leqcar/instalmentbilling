package com.leqcar.instalmentbilling.infrastructure.charges;

import com.leqcar.instalmentbilling.domain.model.charges.ChargesAllocatorService;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.product.Premium;

import java.util.List;

public class FlatChargesAllocatorService implements ChargesAllocatorService {

	@Override
	public void allocateCharges(Policy aPolicy, ChargesRule chargeRule) {

		List<Premium> policyPremiums = aPolicy.getProduct().getPolicyPremiums();

		for (Premium premium : policyPremiums) {


		}
	}

}
