package com.leqcar.instalmentbilling.domain.model.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.leqcar.instalmentbilling.domain.model.charges.Charges;


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
	
	private List<Charges> charges;
	
	private boolean isMaxDeltaCharge;
	
	
	
	public Premium(String locationNo, String coverageObjectNo, String sectionCode, String riskCode, String coverageCode,
			String majorLineCode, String minorLineCode, String classPerilCode, String stateCode, ProductId productId
			, BigDecimal onSetPremiumAmount
			, BigDecimal offSetPremiumAmount) {
		super();
		this.locationNo = locationNo;
		this.coverageObjectNo = coverageObjectNo;
		this.sectionCode = sectionCode;
		this.riskCode = riskCode;
		this.coverageCode = coverageCode;
		this.majorLineCode = majorLineCode;
		this.minorLineCode = minorLineCode;
		this.classPerilCode = classPerilCode;
		this.stateCode = stateCode;
		this.productId = productId;
		this.onSetPremiumAmount = onSetPremiumAmount;
		this.offSetPremiumAmount = offSetPremiumAmount;
		
	}


	protected BigDecimal premiumWrittenAmount() {
		return onSetPremiumAmount.subtract(offSetPremiumAmount);
	}


	protected void calculateDeltaPremiumWrittenAmount(BigDecimal coinsuranceAmount, BigDecimal deltaPremiumWrittenAmount) {
		this.deltaPremiumWrittenAmount = coinsuranceAmount.multiply(premiumWrittenAmount());
	}


	public BigDecimal getDeltaPremiumWrittenAmount() {
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
	
	
	public void addCharges(Charges aCharge) {
		if (charges == null) {
			charges = new ArrayList<>(); 
		}
		charges.add(aCharge);
	}


	public List<Charges> getCharges() {
		return charges;
	}
	
	public BigDecimal calculateSumOfCharges() {
		return charges.stream()
			.map(c -> c.getDeltaChargeAmount())
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}

