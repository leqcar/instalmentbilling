package com.leqcar.instalmentbilling.domain.service;

import java.util.List;

import com.leqcar.instalmentbilling.domain.model.charges.ChargesAllocatorService;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRuleRepository;
import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.policy.PolicyCharges;
import com.leqcar.instalmentbilling.domain.model.product.Premium;
import com.leqcar.instalmentbilling.domain.shared.ChargesAllocatorResolver;

public class ChargesRuleService {

	private ChargesRuleRepository chargeRuleRepository;
	
	private ChargesAllocatorService chargesAllocatorService;
	
	
	public ChargesRuleService(ChargesRuleRepository chargeRuleRepository,
			ChargesAllocatorService chargesAllocatorService) {
		super();
		this.chargeRuleRepository = chargeRuleRepository;
		this.chargesAllocatorService = chargesAllocatorService;
	}

	public void applyCharges(Policy aPolicy) {
		
		//TODO: find charges rule in the repository
		List<ChargesRule> chargesRule = chargeRuleRepository.findEffectiveRulesByProduct();
		
		ChargesAllocatorResolver resolver = new ChargesAllocatorResolver();
		for (ChargesRule chargeRule : chargesRule) {			
			chargesAllocatorService = resolver.apply(chargeRule.getChargeBasis());
			if (chargesAllocatorService != null) {
				chargesAllocatorService.allocateCharges(aPolicy, chargeRule);
			}	
			
			createPolicyCharges(aPolicy, chargeRule);
		}
		
		aPolicy.calculateDeltaBillableAmount();
		
	}

	private void createPolicyCharges(Policy aPolicy, ChargesRule chargeRule) {
	
		Integer roundPrecision = aPolicy.getProduct().getRoundPrecision().getValue();	
		List<Premium> premiums = aPolicy.getProduct().getPolicyPremiums();		
		PolicyCharges policyCharges = new PolicyCharges(chargeRule.getChargeCode()
				, chargeRule.getChargeType()
				, chargeRule.getChargeBasis()
				, premiums);		
		
		policyCharges.calculateChargesValue(chargeRule.getChargeCount());
		policyCharges.calculateChargesAmount(roundPrecision, chargeRule.getRoundRuleType());
		
		aPolicy.addPolicyCharges(policyCharges);
	}
	
}
