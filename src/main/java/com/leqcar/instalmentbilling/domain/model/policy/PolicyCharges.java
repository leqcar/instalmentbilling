package com.leqcar.instalmentbilling.domain.model.policy;

import java.math.BigDecimal;
import java.util.List;

import com.leqcar.instalmentbilling.domain.model.charges.ChargesBasis;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesType;
import com.leqcar.instalmentbilling.domain.model.charges.RoundRuleType;
import com.leqcar.instalmentbilling.domain.model.product.Premium;

//value object
public class PolicyCharges {

	private String chargeCode;
	
	private ChargesType chargesType;
	
	private ChargesBasis chargesBasis;
	
	private BigDecimal newChargeValue;
	
	private BigDecimal oldChargeValue;
	
	private BigDecimal deltaChargeValue;
	
	private BigDecimal newChargeAmount;
	
	private BigDecimal oldChargeAmount;
	
	private BigDecimal deltaChargeAmount;
	
	private List<Premium> premiums;

	public PolicyCharges(String chargeCode, ChargesType chargesType, ChargesBasis chargesBasis,
			List<Premium> premiums) {
		super();
		this.chargeCode = chargeCode;
		this.chargesType = chargesType;
		this.chargesBasis = chargesBasis;
		this.premiums = premiums;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public ChargesType getChargesType() {
		return chargesType;
	}

	public ChargesBasis getChargesBasis() {
		return chargesBasis;
	}

	public BigDecimal getNewChargeValue() {
		return newChargeValue;
	}

	public BigDecimal getOldChargeValue() {
		return oldChargeValue;
	}

	public BigDecimal getDeltaChargeValue() {
		return deltaChargeValue;
	}

	public BigDecimal getNewChargeAmount() {
		return newChargeAmount;
	}

	public BigDecimal getOldChargeAmount() {
		return oldChargeAmount;
	}

	public BigDecimal getDeltaChargeAmount() {
		return deltaChargeAmount;
	}

	public List<Premium> getPremiums() {
		return premiums;
	}
	
	public void calculateChargesAmount(int roundPrecision, RoundRuleType roundRuleType) {
		this.oldChargeAmount = BigDecimal.ZERO;
		this.deltaChargeAmount = calculateDeltaChargesAmount(roundPrecision, roundRuleType);
		this.newChargeAmount = this.oldChargeAmount.add(deltaChargeAmount);
	}
	
	public void calculateChargesValue(BigDecimal chargeCount) {
		if (chargesBasis.equals(ChargesBasis.FLAT)) {
			this.newChargeValue = BigDecimal.ONE.negate();
			this.oldChargeValue = BigDecimal.ONE.negate();
			this.deltaChargeValue = BigDecimal.ZERO;			
		} else {
			this.newChargeValue = chargeCount;
			this.oldChargeValue = BigDecimal.ZERO;
			this.deltaChargeValue = newChargeValue.subtract(oldChargeValue);
		}
	}
	
	private BigDecimal calculateDeltaChargesAmount(int roundPrecision, RoundRuleType roundRuleType) {		
		return premiums.stream()
			.map(p -> p.getCharges().stream()
					.filter(d -> d.getChargeCode().equals(chargeCode))
					.map(c -> c.getDeltaChargeAmount())
					.reduce(BigDecimal.ZERO, (a, b) -> roundRuleType.apply(roundPrecision, a)
							.add(roundRuleType.apply(roundPrecision, b))))
			.reduce(BigDecimal.ZERO, (x, y) -> roundRuleType.apply(roundPrecision, x)
					.add(roundRuleType.apply(roundPrecision, y)));		
	}	
}
