package com.leqcar.instalmentbilling.domain.model.charges;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class ChargesRule {

	private String chargeCode;
	
	private ChargesBasis chargeBasis;
	
	private BigDecimal chargeCount;
	
	private ChargesType chargeType;
	
	private String productCode;
	
	private String locationNumber;
	
	private String sectionCode;
	
	private String riskCode;
	
	private String coverage;
	
	private AttachmentLevel attachmentLevel;
	
	private String calculationFormula;
	
	private Integer calculationSequenceNumber;
	

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


	public String getCoverage() {
		return coverage;
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
	
}
