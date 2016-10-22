package com.leqcar.instalmentbilling.domain.model.product;

import com.leqcar.instalmentbilling.domain.model.charges.Charges;

public class Coverage {

	private Premium premium;

	protected Charges applyCharges() {
		return null;
	}


	protected Premium getPremium() {
		return premium;
	}
	
}
