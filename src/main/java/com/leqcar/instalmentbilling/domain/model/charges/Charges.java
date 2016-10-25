package com.leqcar.instalmentbilling.domain.model.charges;

import java.math.BigDecimal;

public class Charges {

	private String chargeCode;
	//P - Percent; A - Amount
	private String chargeType;
	//C - Charge; T - Tax
	private String chargeCategory;
	
	private BigDecimal newChargeValue;
	private BigDecimal oldChargeValue;
	private BigDecimal deltaChargeValue;
	
	private BigDecimal deltaChargeAmount;
	private BigDecimal newChargeAmount;
	private BigDecimal oldChargeAmount;
	
	public Charges(String chargeCode
			, String chargeType
			, String chargeCategory
			, BigDecimal newChargeValue
			, BigDecimal oldChargeValue
			, BigDecimal deltaChargeValue
			, BigDecimal deltaChargeAmount
			, BigDecimal oldChargeAmount) {
		super();
		this.chargeCode = chargeCode;
		this.chargeType = chargeType;
		this.chargeCategory = chargeCategory;
		this.newChargeValue = newChargeValue;
		this.oldChargeValue = oldChargeValue;
		this.deltaChargeValue = calculateDeltaChargeValue(newChargeValue, oldChargeValue);
		this.deltaChargeAmount = deltaChargeAmount;		
		this.newChargeAmount = calculateDeltaChargeAmount(deltaChargeAmount, oldChargeAmount);
		this.oldChargeAmount = oldChargeAmount;
	}

	public Charges() {

	}

	public String getChargeCode() {
		return chargeCode;
	}

	public String getChargeType() {
		return chargeType;
	}

	public String getChargeCategory() {
		return chargeCategory;
	}

	public BigDecimal getNewChargeValue() {
		return newChargeValue;
	}

	public BigDecimal getOldChargeValue() {
		return oldChargeValue;
	}

	public BigDecimal getDeltaChargeValue() {
		return deltaChargeValue;
	}

	public BigDecimal getDeltaChargeAmount() {
		return deltaChargeAmount;
	}

	public BigDecimal getNewChargeAmount() {
		return newChargeAmount;
	}

	public BigDecimal getOldChargeAmount() {
		return oldChargeAmount;
	}

	private BigDecimal calculateDeltaChargeValue(BigDecimal newChargeValue, BigDecimal oldChargeValue) {
		return newChargeValue.subtract(oldChargeValue);
	}

	private BigDecimal calculateDeltaChargeAmount(BigDecimal deltaChargeAmount, BigDecimal oldChargeAmount) {
		return deltaChargeAmount.subtract(oldChargeAmount);
	}
	
}
