package com.leqcar.instalmentbilling.domain.model.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.leqcar.instalmentbilling.domain.model.quote.WipId;

public class Product {

	private ProductId productId;
	
	private LocalDate effectiveDate;
	
	private RoundPrecision roundPrecision;
	
	private PrimaryMajorLine primaryMajorLine;
	
	private List<Premium> policyPremiums;

	private Coinsurance coinsurance;
	
	private WipId wipId;

	
	public Product(ProductId productId, LocalDate effectiveDate, WipId wipId) {
		this.productId = productId;
		this.effectiveDate = effectiveDate;
		this.wipId = wipId;
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

	public WipId getQuoteId() {
		return wipId;
	}
	
	public boolean hasPolicyPremium() {
		return policyPremiums.isEmpty() ? false : true;
	}

	public void addPremiumList(List<Premium> aPolicyPremiumList) {		
		if (policyPremiums == null) {
			policyPremiums = new ArrayList<>();
		}			
		aPolicyPremiumList.stream()			
			.forEach(premium -> {
				premium.calculateDeltaPremiumWrittenAmount(coinsurance.getCoinsuranceCoeffValue(), premium.premiumWrittenAmount());
				policyPremiums.add(premium);
			});						
	}
	
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public RoundPrecision getRoundPrecision() {
		return roundPrecision;
	}

	public Coinsurance getCoinsurance() {
		return coinsurance;
	}

	public void setCoinsurance(Coinsurance coinsurance) {
		this.coinsurance = coinsurance;
	}
	
	
	
}
