package com.leqcar.instalmentbilling.domain.model.product;

import java.math.BigDecimal;

public class Coinsurance {

	private String code;
	
	private BigDecimal coveragePercentage;
	
	private String indicatorCode;
	
	private BigDecimal signedLinePercentage;

	private BigDecimal coinsuranceCoeffValue = BigDecimal.valueOf(1.0); 
	
	private final static String COINSURANCE_CODE_LEAD = "01";
	
	private final static String COINSURANCE_CODE_FOLLOWER = "02";
	
	private final static String INDICATOR_01 = "01";
	
	private final static String INDICATOR_02 = "02";
	
	//note there are other codes, or set of codes depending on the country
	
	public Coinsurance(String code, BigDecimal coveragePercentage, String indicatorCode,
			BigDecimal signedLinePercentage) {
		super();
		this.code = code;
		this.coveragePercentage = coveragePercentage;
		this.indicatorCode = indicatorCode;
		this.signedLinePercentage = signedLinePercentage;
		this.coinsuranceCoeffValue = calculateCoeffValue(code, 
				indicatorCode, 
				coveragePercentage, 
				signedLinePercentage);
	}

	private BigDecimal calculateCoeffValue(String aCode, 
			String aIndicatorCode, 
			BigDecimal aCoveragePercentage,
			BigDecimal aSignedLinePercentage) {
		
		BigDecimal coinsuranceCoeffValue = BigDecimal.valueOf(1.0);
		
		switch (aIndicatorCode) {
		case "01":
			if (code.equals("03") || (code.equals("04"))) {
				//TODO: Put the derive value
				return null;
			}
		case "02" :
			if (aCode.equals("01") || (aCode.equals("02"))) {
				//TODO: Put the derive value
				return null;
			}

		default:
			break;
		}
		
		return coinsuranceCoeffValue;
	}

	protected BigDecimal getCoinsuranceCoeffValue() {
		return coinsuranceCoeffValue;
	}

}
