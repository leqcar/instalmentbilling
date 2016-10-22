package com.leqcar.instalmentbilling.domain.model.product;

import java.math.BigDecimal;

import com.leqcar.instalmentbilling.domain.model.charges.Charges;

public class Section {

	private Risk risk;

	private Premium premium;
	
	public Risk getRisk() {
		return risk;
	}

	public Charges applyCharges() {
		
		return null;
	}
	
	protected BigDecimal getDeltaPremiumAmount() {
		return risk.getDeltaPremiumAmount();
	}
}
