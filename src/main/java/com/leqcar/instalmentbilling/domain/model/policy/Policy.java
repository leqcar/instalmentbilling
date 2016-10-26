package com.leqcar.instalmentbilling.domain.model.policy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.leqcar.instalmentbilling.domain.model.charges.ChargesType;
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
	
	private List<PolicyCharges> policyCharges;
	
	private BigDecimal deltaBillableAmount;
	
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
		
	public BigDecimal getDeltaBillableAmount() {
		return deltaBillableAmount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void addPolicyCharges(PolicyCharges policyCharges) {
		if (this.policyCharges == null) {
			this.policyCharges = new ArrayList<>();
		}
		this.policyCharges.add(policyCharges);
	}

	public List<PolicyCharges> getPolicyCharges() {
		return policyCharges;
	}	
	
	private BigDecimal calculateDeltaTaxAmount() {
		return policyCharges.stream()
			.filter(c -> c.getChargesType().equals(ChargesType.TAX))
			.map(c -> c.getDeltaChargeAmount())
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	private BigDecimal calculateDeltaChargeAmount() {
		return policyCharges.stream()
				.filter(c -> c.getChargesType().equals(ChargesType.CHARGE))
				.map(c -> c.getDeltaChargeAmount())
				.reduce(BigDecimal.ZERO, BigDecimal::add);		
	}
	
	public void calculateDeltaBillableAmount() {		
		BigDecimal sumOfDeltaPremiumWritten = product.calculateSumOfDeltaPremiumWritten();
		this.deltaBillableAmount = sumOfDeltaPremiumWritten
				.add(calculateDeltaChargeAmount())
				.add(calculateDeltaTaxAmount());
	}
}
