package com.leqcar.instalmentbilling.domain.model.product;

import java.math.BigDecimal;


//TODO : Analyze how to handle the State code, put here the implementation;

public class Premium {
	
	private String locationNo;
	
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

	private String stateCode;

	private ProductId productId;
	
	
	protected BigDecimal premiumWrittenAmount() {
		return onSetPremiumAmount.subtract(offSetPremiumAmount);
	}


	protected void calculateDeltaPremiumWrittenAmount(BigDecimal coinsuranceAmount, BigDecimal deltaPremiumWrittenAmount) {
		this.deltaPremiumWrittenAmount = coinsuranceAmount.multiply(premiumWrittenAmount());
	}


	protected BigDecimal getDeltaPremiumWrittenAmount() {
		return deltaPremiumWrittenAmount;
	}


	public String getLocationNo() {
		return locationNo;
	}


	public String getSectionCode() {
		return sectionCode;
	}


	public String getRiskCode() {
		return riskCode;
	}


	public String getCoverageCode() {
		return coverageCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public ProductId getProductId() {
		return productId;
	}
}

