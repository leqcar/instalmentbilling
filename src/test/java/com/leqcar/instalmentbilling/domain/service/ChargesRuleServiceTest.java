package com.leqcar.instalmentbilling.domain.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevel;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesAllocatorService;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesBasis;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRuleRepository;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesType;
import com.leqcar.instalmentbilling.domain.model.charges.RoundRuleType;
import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.product.Coinsurance;
import com.leqcar.instalmentbilling.domain.model.product.Premium;
import com.leqcar.instalmentbilling.domain.model.product.Product;
import com.leqcar.instalmentbilling.domain.model.product.ProductId;
import com.leqcar.instalmentbilling.domain.model.product.RoundPrecision;
import com.leqcar.instalmentbilling.domain.model.quote.WipId;

public class ChargesRuleServiceTest {

	@Mock
	private ChargesRuleRepository chargeRuleRepository;
	@Mock
	private ChargesAllocatorService chargesAllocatorService;
	
	
	private MockPolicy policy;
	private MockCoinsurance coinsurance;
	private MockRoundPrecision roundPrecision;
	
	@Before
	public void setUp() {
		policy = new MockPolicy();
		coinsurance = new MockCoinsurance();
		roundPrecision = new MockRoundPrecision();
		chargeRuleRepository = mock(ChargesRuleRepository.class);
		chargesAllocatorService = mock(ChargesAllocatorService.class);		
	}
	
	
	@Test
	public void testApplyCharges() {
		ChargesRuleService service = new ChargesRuleService(chargeRuleRepository, chargesAllocatorService);

		when(chargeRuleRepository.findEffectiveRulesByProduct()).thenReturn(createMockChargeRuleList());
		
		Policy aPolicy = policy.createPolicy();
		
		aPolicy.getProduct().setCoinsurance(coinsurance.createCoinsurance());
		aPolicy.getProduct().setRoundPrecision(roundPrecision.createRoundPrecision());
		
		service.applyCharges(aPolicy);
				
	}

	private List<ChargesRule> createMockChargeRuleList() {

		List<ChargesRule> rules = new ArrayList<>();
		
		ChargesRule chargesRule09 = new ChargesRule("09"
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
		
		ChargesRule chargesRule11 = new ChargesRule("11"
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
		
		ChargesRule chargesRule18 = new ChargesRule("18"
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
		
		rules.add(chargesRule11);
		rules.add(chargesRule09);
		rules.add(chargesRule18);
		
		return rules;
	}
	
	class MockPolicy {
		
		public Policy createPolicy() {
			Policy policy = new Policy(LocalDate.now()
					, LocalDate.now()
					, LocalDate.now()
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
