package com.leqcar.instalmentbilling.domain.model.policy;

import com.leqcar.instalmentbilling.domain.model.product.Product;
import com.leqcar.instalmentbilling.domain.model.quote.QuoteId;

import java.time.LocalDate;

public class Policy {

	private LocalDate expirationDate;
	private LocalDate effectiveDate;
	
	//used for new business
	private LocalDate inceptionDate;
	//used for endorments
	private LocalDate transactionDate;
	
	//TODO: change this to WipId class
	private QuoteId quoteId;
	
	private Product product;
	
	private Commission commission;
	
	private TransactionType transactionType;

	public Policy(LocalDate expirationDate, 
			LocalDate effectiveDate, 
			LocalDate inceptionDate, 
			QuoteId quoteId,
			Product product) {
		super();
		this.expirationDate = expirationDate;
		this.effectiveDate = effectiveDate;
		this.inceptionDate = inceptionDate;
		this.quoteId = quoteId;
		this.product = product;
	}

	public QuoteId getQuoteId() {
		return quoteId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
