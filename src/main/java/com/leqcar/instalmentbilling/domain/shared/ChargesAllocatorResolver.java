package com.leqcar.instalmentbilling.domain.shared;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.leqcar.instalmentbilling.domain.model.charges.ChargesAllocatorService;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesBasis;
import com.leqcar.instalmentbilling.infrastructure.charges.FlatChargesAllocatorService;
import com.leqcar.instalmentbilling.infrastructure.charges.PercentChargesAllocatorService;

public class ChargesAllocatorResolver implements Function<ChargesBasis, ChargesAllocatorService> {

	@Override
	public ChargesAllocatorService apply(ChargesBasis chargesBasis) {
		List<ChargesBasis> basis =  Arrays.asList(ChargesBasis.values());
		ChargesAllocatorService chargesAllocatorService = null;
		for (ChargesBasis chargesBasisObj : basis) {
			if (chargesBasisObj.sameValueAs(ChargesBasis.PERCENTAGE)) {
				chargesAllocatorService =  new PercentChargesAllocatorService();
				break;
			} else if (chargesBasisObj.sameValueAs(ChargesBasis.FLAT)) {
				chargesAllocatorService =  new FlatChargesAllocatorService();
				break;
			}
		}
		return chargesAllocatorService;
	}


}
