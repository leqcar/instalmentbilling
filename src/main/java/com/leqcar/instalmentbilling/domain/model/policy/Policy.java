package com.leqcar.instalmentbilling.domain.model.policy;

import java.time.LocalDate;

import com.leqcar.instalmentbilling.domain.model.product.Product;
import com.leqcar.instalmentbilling.domain.model.quote.WipId;

public class Policy {

	private LocalDate expirationDate;
	private LocalDate effectiveDate;
	
	//used for new business
	private LocalDate inceptionDate;
	//used for endorsements
	private LocalDate transactionDate;

	private WipId wipId;
	
	private Product product;
	
	private Commission commission;
	
	private TransactionType transactionType;
	
	public Policy(LocalDate expirationDate, 
			LocalDate effectiveDate, 
			LocalDate inceptionDate, 
			WipId wipId,
			Product product) {
		super();
		this.expirationDate = expirationDate;
		this.effectiveDate = effectiveDate;
		this.inceptionDate = inceptionDate;
		this.wipId = wipId;
		this.product = product;
	}

	public WipId getWipId() {
		return wipId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public LocalDate getInceptionDate() {
		return inceptionDate;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public Commission getCommission() {
		return commission;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}
	
}
