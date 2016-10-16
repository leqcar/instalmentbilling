package com.leqcar.instalmentbilling.application;

import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.product.Product;
import com.leqcar.instalmentbilling.domain.model.quote.QuoteId;
import com.leqcar.instalmentbilling.domain.service.PolicyRetrievalService;
import com.leqcar.instalmentbilling.interfaces.InstalmentRequest;

public class DefaultBillingService implements BillingService {

	private PolicyRetrievalService policyService;
	
	@Override
	public void requestInstallmentBilling(InstalmentRequest request) {

		QuoteId quoteId = new QuoteId(request.getWipNo(), request.getWipIterationNumber());
		Policy policyRequest = new Policy(request.getExpirationDate(), 
				request.getEffectiveDate(), 
				request.getInceptionDate(), 
				quoteId, 
				new Product(request.getProductCode()
						, request.getEffectiveDate()
						, quoteId));		
		
		Policy policy = policyService.retrievePolicyInformation(policyRequest);
	}


}

