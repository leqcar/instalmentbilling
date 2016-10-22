package com.leqcar.instalmentbilling.domain.model.product;

import java.math.BigDecimal;
import java.util.List;

import com.leqcar.instalmentbilling.domain.model.charges.Charges;

public class Risk {
	
	private List<Coverage> coverages;
	
	protected List<Charges> applyCharges() {
		return  null;
	}
	
	protected BigDecimal getDeltaPremiumAmount() {
		return coverages.stream()
			.map(c -> c.getPremium().getDeltaPremiumWrittenAmount())
			.reduce(BigDecimal.ZERO, BigDecimal::add);		
	}
}
