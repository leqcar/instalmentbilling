package com.leqcar.instalmentbilling.domain.service;

import java.util.List;

import com.leqcar.instalmentbilling.domain.model.product.Premium;
import com.leqcar.instalmentbilling.domain.model.product.Product;
import com.leqcar.instalmentbilling.domain.model.product.ProductRepository;

public class ProductRetrievalService {

	private ProductRepository productRepository;
	
	private PremiumRetrievalService premiumRetrievalService;

	public ProductRetrievalService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product retrieveProductInformation(Product aProduct) {

		Product product = productRepository.findByCode(aProduct);
		
		if (!product.hasPolicyPremium()) {
			List<Premium> policyPremiums = premiumRetrievalService.retrievePolicyPremium(aProduct);
			product.addPremiumList(policyPremiums);
		}
		return product;
	}
	

	
}
