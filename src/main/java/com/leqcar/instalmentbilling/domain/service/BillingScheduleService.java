package com.leqcar.instalmentbilling.domain.service;

import com.leqcar.instalmentbilling.domain.model.policy.Policy;

public class BillingScheduleService {

	private InstalmentProcessor<Policy> instalmentProcessor;
	
	public void generateInstallment(Policy aPolicy) {
		instalmentProcessor.process(aPolicy);
	}

}
