package com.leqcar.instalmentbilling.domain.model.product;

import java.math.BigDecimal;

public class RoundPrecision {

	private BigDecimal value;
	
	private BigDecimal chargeValue;
	
	private BigDecimal billableAmount;
	
	//TODO: Has an installment Rule
	  //call getInstallmentRule to set the premium round precision. (roundPrec)
	  //if set to -1 then use the tproduct default value
	
	  //call getInstallmentRule to set the charges/Taxes round precision. (roundPrecCharge)
	  //if set to -1 then use the tproduct default value else use the rule set value
	
}
