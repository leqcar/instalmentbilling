package com.leqcar.instalmentbilling.infrastructure.charges;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevel;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesBasis;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesType;
import com.leqcar.instalmentbilling.domain.model.charges.RoundRuleType;
import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.product.Coinsurance;
import com.leqcar.instalmentbilling.domain.model.product.Premium;
import com.leqcar.instalmentbilling.domain.model.product.Product;
import com.leqcar.instalmentbilling.domain.model.product.ProductId;
import com.leqcar.instalmentbilling.domain.model.product.RoundPrecision;
import com.leqcar.instalmentbilling.domain.model.quote.WipId;

public class PercentChargesAllocatorServiceTest {

	private MockPolicy policy;
	private MockCoinsurance coinsurance;
	private MockRoundPrecision roundPrecision;
	
	@Before
	public void setUp() {
		policy = new MockPolicy();
		coinsurance = new MockCoinsurance();
		roundPrecision = new MockRoundPrecision();
		
	}
	
	@Test
	public void testApplyChargesForProductLevel() {
		
		PercentChargesAllocatorService service = new PercentChargesAllocatorService();
		
		Policy aPolicy = policy.createPolicy();
		
		ChargesRule chargesRule = new ChargesRule("1234"
				,  ChargesBasis.PERCENTAGE
				, BigDecimal.ONE
				, ChargesType.CHARGE
				, "XYZ"
				, "1"
				, "123"
				, "1234"
				, "12345"
				, "999"
				, AttachmentLevel.PRODUCT_LEVEL
				, RoundRuleType.NORMAL
				, "1 + 2");
		
		boolean hasNoChargesYet = aPolicy.getProduct().getPolicyPremiums().stream()
				.map(e -> e.getCharges() != null).findAny().orElse(false);		
		assertFalse(hasNoChargesYet);
		
		service.allocateCharges(aPolicy, chargesRule);
		
		boolean actual = aPolicy.getProduct().getPolicyPremiums().stream()
							.map(e -> e.getCharges() != null).findAny().orElse(false);
		assertTrue(actual);
	}
	
	class MockPolicy {
		
		public Policy createPolicy() {
			Policy policy = new Policy(LocalDate.now()
					, LocalDate.now()
					, LocalDate.now()
					, 12
					, new WipId("0001", "1")
					, new Product(new ProductId("XYZ")
							, LocalDate.now()
							, new WipId("0001", "1")));
				
			policy.getProduct().setCoinsurance(coinsurance.createCoinsurance());
			policy.getProduct().setRoundPrecision(roundPrecision.createRoundPrecision());
			
			Premium premium = new Premium("1"
					, "coverageObjectNo"
					, "123"
					, "1234"
					, "12345"
					, "majorLineCode"
					, "minorLineCode"
					, "classPerilCode"
					, "stateCode"
					, new ProductId("XYZ")
					, new BigDecimal(1000)
					, new BigDecimal(10));
			
			
			policy.getProduct().addPremiumList(Arrays.asList(premium));
			
			return policy;
		}
	}
	
	class MockCoinsurance {
		
		public Coinsurance createCoinsurance() {
			Coinsurance coinsurance = new Coinsurance("01"
					, BigDecimal.ONE
					, "indicatorCode"
					,  BigDecimal.ONE);
			
			return coinsurance;
		}
	}
	
	class MockRoundPrecision {
		public RoundPrecision createRoundPrecision() {
			RoundPrecision roundPrecision = new RoundPrecision(
					5, 
					BigDecimal.TEN, 
					null);
			
			return roundPrecision;
		}
	}
}
