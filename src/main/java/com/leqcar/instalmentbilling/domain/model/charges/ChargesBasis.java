package com.leqcar.instalmentbilling.domain.model.charges;

public enum ChargesBasis {

	PERCENTAGE("20") 	
	, FLAT("02") 
	, AMORTIZATION("30"); 
	
	private String chargeBasis;
	
	private ChargesBasis(String chargeBasis) {
		this.chargeBasis = chargeBasis;
	}
	
	public String getChargeBasis() {
		return chargeBasis;
	}


	public boolean sameValueAs(ChargesBasis otherChargeBasis) {
		return this.equals(otherChargeBasis);
	}
	
	public static ChargesBasis get(String basis) {
		return ChargesBasis.valueOf(basis);		
	}
}
