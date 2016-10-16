package com.leqcar.instalmentbilling.domain.model.product;

import java.math.BigDecimal;


//TODO : Analyze how to handle the State code, put here the implementation;

public class Premium {

	private Location location;
	
	private String sectionObjectNo;
	
	private String riskObjectNo;
	
	private String coverageObjectNo;
	
	private BigDecimal onSetPremiumAmount;
	
	private BigDecimal offSetPremiumAmount;

	private BigDecimal deltaPremiumWrittenAmount;
	
	private String sectionCode;
	
	private String riskCode;
	
	private String coverageCode;
	
	private String majorLineCode;
	
	private String minorLineCode;
	
	private String classPerilCode;
	
	
	protected BigDecimal premiumWrittenAmount() {
		return onSetPremiumAmount.subtract(offSetPremiumAmount);
	}


	protected void calculateDeltaPremiumWrittenAmount(BigDecimal coinsuranceAmount, BigDecimal deltaPremiumWrittenAmount) {
		this.deltaPremiumWrittenAmount = coinsuranceAmount.multiply(premiumWrittenAmount());
	}

}

