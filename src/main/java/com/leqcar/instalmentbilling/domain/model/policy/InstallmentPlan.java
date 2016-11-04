package com.leqcar.instalmentbilling.domain.model.policy;

import java.math.BigDecimal;

public class InstallmentPlan {

	private int installmentNumber;
	
	private BigDecimal deltaPremiumWrittenAmount;

	public InstallmentPlan(int installmentNumber, BigDecimal deltaPremiumWrittenAmount) {
		super();
		this.installmentNumber = installmentNumber;
		this.deltaPremiumWrittenAmount = deltaPremiumWrittenAmount;
	}
	
}
