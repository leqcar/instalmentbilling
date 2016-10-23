package com.leqcar.instalmentbilling.domain.service;

import java.util.List;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevel;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesAllocatorService;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRuleRepository;
import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.shared.ChargesAllocatorResolver;

public class ChargesRuleService {

	private ChargesRuleRepository chargeRuleRepository;
	
	private ChargesAllocatorService chargesAllocatorService;
	
	public void applyCharges(Policy aPolicy) {
		
		//TODO: find charges rule in the repository
		List<ChargesRule> chargesRule = chargeRuleRepository.findEffectiveRulesByProduct();
		
		ChargesAllocatorResolver resolver = new ChargesAllocatorResolver();
		for (ChargesRule chargeRule : chargesRule) {			
			chargesAllocatorService = resolver.apply(chargeRule.getChargeBasis());
			if (chargesAllocatorService != null) {

				chargesAllocatorService.allocateCharges(aPolicy, chargeRule);
			}			
		}
		
	}
	
}
