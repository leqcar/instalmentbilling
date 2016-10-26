package com.leqcar.instalmentbilling.domain.model.charges;

import java.math.BigDecimal;

public class ChargesRule {

	private String chargeCode;
	
	private ChargesBasis chargeBasis;
	
	private BigDecimal chargeCount;
	
	private ChargesType chargeType;
	
	private String productCode;
	
	private String locationNumber;
	
	private String sectionCode;
	
	private String riskCode;
	
	private String coverageCode;

	private String stateCode;
	
	private AttachmentLevel attachmentLevel;
	
	private RoundRuleType roundRuleType;
	
	private String calculationFormula;
	
	private Integer calculationSequenceNumber;
	



	public ChargesRule(String chargeCode, ChargesBasis chargeBasis, BigDecimal chargeCount, ChargesType chargeType,
			String productCode, String locationNumber, String sectionCode, String riskCode, String coverageCode,
			String stateCode, AttachmentLevel attachmentLevel, RoundRuleType roundRuleType, String calculationFormula) {
		super();
		this.chargeCode = chargeCode;
		this.chargeBasis = chargeBasis;
		this.chargeCount = chargeCount;
		this.chargeType = chargeType;
		this.productCode = productCode;
		this.locationNumber = locationNumber;
		this.sectionCode = sectionCode;
		this.riskCode = riskCode;
		this.coverageCode = coverageCode;
		this.stateCode = stateCode;
		this.attachmentLevel = attachmentLevel;
		this.roundRuleType = roundRuleType;
		this.calculationFormula = calculationFormula;
	}


	public ChargesBasis getChargeBasis() {
		return chargeBasis;
	}


	public String getChargeCode() {
		return chargeCode;
	}


	public BigDecimal getChargeCount() {
		return chargeCount;
	}


	public ChargesType getChargeType() {
		return chargeType;
	}


	public String getProductCode() {
		return productCode;
	}


	public String getLocationNumber() {
		return locationNumber;
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


	public AttachmentLevel getAttachmentLevel() {
		return attachmentLevel;
	}


	public String getCalculationFormula() {
		return calculationFormula;
	}


	public Integer getCalculationSequenceNumber() {
		return calculationSequenceNumber;
	}

	public String getStateCode() {
		return stateCode;
	}
	
	public BigDecimal applyRounding(Integer roundPrecision, BigDecimal amount) {
		return roundRuleType.apply(roundPrecision, amount);
	}


	public RoundRuleType getRoundRuleType() {
		return roundRuleType;
	}
		
}
