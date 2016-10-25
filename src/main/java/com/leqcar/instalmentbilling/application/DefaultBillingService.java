package com.leqcar.instalmentbilling.application;

import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.product.Product;
import com.leqcar.instalmentbilling.domain.model.product.ProductId;
import com.leqcar.instalmentbilling.domain.model.quote.WipId;
import com.leqcar.instalmentbilling.domain.service.ChargesRuleService;
import com.leqcar.instalmentbilling.domain.service.PolicyRetrievalService;
import com.leqcar.instalmentbilling.interfaces.InstalmentRequest;

public class DefaultBillingService implements BillingService {

	private PolicyRetrievalService policyService;
	
	private ChargesRuleService chargesRuleService;
	
	@Override
	public void requestInstallmentBilling(InstalmentRequest request) {

		WipId wipId = new WipId(request.getWipNo(), request.getWipIterationNumber());
		Policy policyRequest = new Policy(request.getExpirationDate(), 
				request.getEffectiveDate(), 
				request.getInceptionDate(), 
				wipId, 
				new Product(new ProductId(request.getProductCode())
						, request.getEffectiveDate()
						, wipId));		
		
		Policy policy = policyService.retrievePolicyInformation(policyRequest);
		
		chargesRuleService.applyCharges(policy);
		
		
	}


}

