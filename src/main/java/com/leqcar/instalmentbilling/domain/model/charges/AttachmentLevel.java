package com.leqcar.instalmentbilling.domain.model.charges;

public enum AttachmentLevel  {
	
	PRODUCT_LEVEL("P"),
	SECTION_LEVEL("S"),
	RISK_LEVEL("R"),
	COVERAGE_LEVEL("C");

	
	private String code;

	private AttachmentLevel(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public boolean sameValueAs(AttachmentLevel otherAttachmentLevel) {
		return this.equals(otherAttachmentLevel);
	}
	
}
