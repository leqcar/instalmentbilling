package com.leqcar.instalmentbilling.domain.model.policy;

import java.math.BigDecimal;
import java.util.List;

public class PolicyInstallment {

	BillingCycleId billingCycleId;
	
	List<InstallmentPlan> installmentPlans;
	
	private int numberOfInstallement;
	
	private BigDecimal installmentPremiumWrittenAmount;
	
	public PolicyInstallment(int numberOfInstallement
			, BigDecimal installmentPremiumWrittenAmount
			, BillingCycleId billingCycleId
			, List<InstallmentPlan> installmentPlans) {
		super();
		this.numberOfInstallement = numberOfInstallement;
		this.installmentPremiumWrittenAmount = installmentPremiumWrittenAmount;
		this.billingCycleId = billingCycleId;
		this.installmentPlans = installmentPlans;
	}
	
	public void generateInstallment() {
		for (int installmentNumber = 0; installmentNumber < numberOfInstallement; installmentNumber++) {
			InstallmentPlan installmentPlan = new InstallmentPlan(installmentNumber
					, installmentPremiumWrittenAmount );
			addInstallment(installmentPlan);
		}
	}
	private void addInstallment(InstallmentPlan installmentPlan) {
		this.installmentPlans.add(installmentPlan);
	}
}
