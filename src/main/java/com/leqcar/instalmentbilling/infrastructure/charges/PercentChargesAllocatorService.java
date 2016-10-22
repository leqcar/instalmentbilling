package com.leqcar.instalmentbilling.infrastructure.charges;

import java.util.List;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevel;
import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevelMatcher;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesAllocatorService;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.product.Premium;

public class PercentChargesAllocatorService implements ChargesAllocatorService {

	AttachmentLevelMatcher attachLevelMatcher;
	
	@Override
	public void allocateCharges(Policy aPolicy, ChargesRule chargeRule) {

		List<Premium> policyPremiums = aPolicy.getProduct().getPolicyPremiums();

		for (Premium premium : policyPremiums) {
			
			if (AttachmentLevel.SECTION_LEVEL.sameValueAs(chargeRule.getAttachmentLevel())) {
				attachLevelMatcher.test(chargeRule, premium);
			}
			
			
		}

		
	}

}
