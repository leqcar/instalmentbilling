package com.leqcar.instalmentbilling.domain.model.quote;

public class WipId {

	private String wipNumber;
	
	private String wipIterationNumber;

	public WipId(String wipNumber, String wipIterationNumber) {
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
