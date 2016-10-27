package com.leqcar.instalmentbilling.infrastructure.charges;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.leqcar.instalmentbilling.domain.model.charges.Charges;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesAllocatorService;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.product.Premium;

public class FlatChargesAllocatorService implements ChargesAllocatorService {

	@Override
	public void allocateCharges(Policy aPolicy, ChargesRule chargesRule) {

		List<Premium> policyPremiums = aPolicy.getProduct().getPolicyPremiums();
		BigDecimal policyDeltaPremiumWrittenAmount =  aPolicy.getProduct().calculateSumOfDeltaPremiumWritten();
		
		for (Premium premium : policyPremiums) {
			
			Charges charges = new Charges(chargesRule.getChargeCode(), 
					chargesRule.getChargeBasis().name(), 
					chargesRule.getChargeType().name(), 
					chargesRule.getChargeCount(),
					BigDecimal.valueOf(0.0), 					
					BigDecimal.valueOf(0.0), 
					calculateDeltaChargeAmount(policyDeltaPremiumWrittenAmount
							,premium.getDeltaPremiumWrittenAmount()
							,chargesRule.getChargeCount()), 
					BigDecimal.valueOf(0.0));
						
			premium.addCharges(charges);
		}
				
		//The logic is based from the legacy code, this is a workaround or a bugfix for missing decimal value.
		// Root cause maybe on using Double instead of BigDecimal - THis Can Be removed
		BigDecimal sumOfCharges = aPolicy.getProduct().calculateSumOfDeltaChargesAmount();									
		BigDecimal diff = chargesRule.getChargeCount().subtract(sumOfCharges);

		Comparator<Charges> deltaChargeAmountComparator = Comparator.comparing(Charges::getDeltaChargeAmount);
		
		policyPremiums.stream()
			.map(p -> p.getCharges().stream()
					.collect(Collectors.maxBy(deltaChargeAmountComparator))
					.orElse(null))
			.findFirst().ifPresent(c -> c.setDeltaChargeAmount(c.getDeltaChargeAmount().add(diff)));
		//****
	}

	private BigDecimal calculateDeltaChargeAmount(BigDecimal policyDeltaPremiumWrittenAmount
			, BigDecimal deltaPremiumWrittenAmount
			, BigDecimal chargeCount) {
		
		BigDecimal pwaAndChargeCount = deltaPremiumWrittenAmount.multiply(chargeCount);
		return pwaAndChargeCount.divide(policyDeltaPremiumWrittenAmount, 5, RoundingMode.HALF_UP);	
		
	}

}
