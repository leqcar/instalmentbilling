package com.leqcar.instalmentbilling.domain.model.product;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.leqcar.instalmentbilling.domain.model.quote.QuoteId;

public class Product {

	private ProductId productId;
	
	private LocalDate effectiveDate;
	
	private RoundPrecision roundPrecision;
	
	private PrimaryMajorLine primaryMajorLine;
	
	private List<Premium> policyPremiums = Collections.emptyList();

	private Coinsurance coinsurance;
	
	private QuoteId quoteId;

	
	public Product(ProductId productId, LocalDate effectiveDate, QuoteId quoteId) {
		this.productId = productId;
		this.effectiveDate = effectiveDate;
		this.quoteId = quoteId;
	}

	public void setPrimaryMajorLine(PrimaryMajorLine primaryMajorLine) {
		this.primaryMajorLine = primaryMajorLine;
	}

	public PrimaryMajorLine getPrimaryMajorLine() {
		return primaryMajorLine;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setRoundPrecision(RoundPrecision roundPrecision) {
		this.roundPrecision = roundPrecision;
	}

	public ProductId getProductId() {
		return productId;
	}

	public List<Premium> getPolicyPremiums() {
		return policyPremiums;
	}

	public QuoteId getQuoteId() {
		return quoteId;
	}
	
	public boolean hasPolicyPremium() {
		return policyPremiums.isEmpty() ? false : true;
	}

	public void addPremiumList(List<Premium> aPolicyPremiumList) {
		aPolicyPremiumList.stream()
			.forEach(premium -> {
				premium.calculateDeltaPremiumWrittenAmount(coinsurance.getCoinsuranceCoeffValue()
					, premium.premiumWrittenAmount());
				policyPremiums.add(premium);
			});
	}


	
}
