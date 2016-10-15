package com.leqcar.instalmentbilling.domain.model.quote;

public class QuoteId {

	private String wipNumber;
	
	private String wipIterationNumber;

	public QuoteId(String wipNumber, String wipIterationNumber) {
		super();
		this.wipNumber = wipNumber;
		this.wipIterationNumber = wipIterationNumber;
	}

	public String getWipNumber() {
		return wipNumber;
	}

	public String getWipIterationNumber() {
		return wipIterationNumber;
	}
		
}
