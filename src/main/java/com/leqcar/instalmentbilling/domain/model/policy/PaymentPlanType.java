package com.leqcar.instalmentbilling.domain.model.policy;

public enum PaymentPlanType {

	VARIABLE_PLAN(1), 
	DEPOSIT_PLAN(2);
	
	private int paymentPlanType;

	private PaymentPlanType(int paymentPlanType) {
		this.paymentPlanType = paymentPlanType;
	}
	
	
	public int getPaymentPlanType() {
		return paymentPlanType;
	}


	public boolean sameValueAs(PaymentPlanType otherPaymentPlanType) {
		return this.equals(otherPaymentPlanType);
	}
}
