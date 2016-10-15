package com.leqcar.instalmentbilling.domain.service;

import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.policy.PolicyRepository;
import com.leqcar.instalmentbilling.domain.model.product.Product;

public class PolicyRetrievalService {

	private PolicyRepository policyRepository;
	
	private ProductRetrievalService productRetrievalService;

	public PolicyRetrievalService(ProductRetrievalService productRetrievalService
			, PolicyRepository policyRepository) {
		super();
		this.productRetrievalService = productRetrievalService;
		this.policyRepository = policyRepository;
	}


	public Policy retrievePolicyInformation(Policy aPolicy) {
	
		Policy policy = policyRepository.findByWip(aPolicy);
		if (policy == null) {
			throw new IllegalStateException();
		}
		if (policy.getProduct() == null) {
			Product product = productRetrievalService.retrieveProductInformation(aPolicy.getProduct());
			policy.setProduct(product);
		}
		return  policy;
	}
	
}
