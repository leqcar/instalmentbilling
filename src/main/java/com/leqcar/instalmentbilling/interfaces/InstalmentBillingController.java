package com.leqcar.instalmentbilling.interfaces;

import javax.validation.Valid;

import org.springframework.web.context.annotation.RequestScope;

import com.leqcar.instalmentbilling.application.BillingService;

public class InstalmentBillingController {

	private BillingService billingService;
	
	
	@RequestScope
	public void requestInstalmentBilling(@Valid InstalmentRequest request) {
		
		billingService.requestInstallmentBilling(request);
	}
}
