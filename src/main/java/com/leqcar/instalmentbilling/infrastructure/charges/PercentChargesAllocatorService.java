package com.leqcar.instalmentbilling.infrastructure.charges;

import java.util.List;

import com.leqcar.instalmentbilling.domain.model.charges.*;
import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.product.Premium;
import com.leqcar.instalmentbilling.domain.service.AttachmentLevelCodeMatcherService;
import com.leqcar.instalmentbilling.infixpostfix.InfixToPostfix;

import static javax.swing.UIManager.get;

public class PercentChargesAllocatorService implements ChargesAllocatorService {
	
	@Override
	public void allocateCharges(Policy aPolicy, ChargesRule chargesRule) {

		AttachmentLevelCodeMatcherService attachmentLevelCodeMatcherService = new AttachmentLevelCodeMatcherService();
		List<Premium> policyPremiums = aPolicy.getProduct().getPolicyPremiums();

		for (Premium premium : policyPremiums) {

			if (attachmentLevelCodeMatcherService.verifyAttachLevelCodeMatchOf(chargesRule, premium)) {
				String postfix = InfixToPostfix.INSTANCE.apply(chargesRule.getCalculationFormula());
			} else {
				continue;
			}
		}
	}

}

