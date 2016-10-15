package com.leqcar.instalmentbilling.interfaces;

import java.time.LocalDate;

public class InstalmentRequest {

	private String wipNo;
	
	private String wipIterationNumber;
	
	private LocalDate inceptionDate;
	
	private LocalDate expirationDate;
	
	private LocalDate effectiveDate;
	
	private	int numberOfInstallment;

	private String productCode;
	
	public String getWipNo() {
		return wipNo;
	}

	public String getWipIterationNumber() {
		return wipIterationNumber;
	}

	public LocalDate getInceptionDate() {
		return inceptionDate;
	}

	public int getNumberOfInstallment() {
		return numberOfInstallment;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public String getProductCode() {
		return productCode;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	
}
