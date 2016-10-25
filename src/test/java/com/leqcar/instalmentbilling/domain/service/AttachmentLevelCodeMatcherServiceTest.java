package com.leqcar.instalmentbilling.domain.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.leqcar.instalmentbilling.domain.model.charges.AttachmentLevel;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesBasis;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesType;
import com.leqcar.instalmentbilling.domain.model.charges.RoundRuleType;
import com.leqcar.instalmentbilling.domain.model.product.Premium;
import com.leqcar.instalmentbilling.domain.model.product.ProductId;

public class AttachmentLevelCodeMatcherServiceTest {

	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testProductLevelMatchesWhichReturnsTrue() {
		AttachmentLevelCodeMatcherService aService = new AttachmentLevelCodeMatcherService();
		
		ChargesRule chargesRule = new ChargesRule("09"
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
				, "1 + 1");
		
		boolean actual = aService.verifyAttachLevelCodeMatchOf(chargesRule
				, new MockPremium().createPremium());
		assertTrue(actual);
	}
	
	@Test
	public void testSectionLevelMatchesWhichReturnsTrue() {
		AttachmentLevelCodeMatcherService aService = new AttachmentLevelCodeMatcherService();
		
		ChargesRule chargesRule = new ChargesRule("1234"
				,  ChargesBasis.PERCENTAGE
				, BigDecimal.ONE
				, ChargesType.CHARGE
				, "XYZ"
				, "1"
				, "123"
				, "1234"
				, "12345"
				, "stateCode"
				, AttachmentLevel.SECTION_LEVEL
				, RoundRuleType.NORMAL
				, "1 + 1");		
		
		
		boolean actual = aService.verifyAttachLevelCodeMatchOf(chargesRule
				, new MockPremium().createPremium());
		assertTrue(actual);
	}
	
	@Test
	public void testSectionLevelMatcher_StateNotMatch_WhichReturnsFalse() {
		AttachmentLevelCodeMatcherService aService = new AttachmentLevelCodeMatcherService();
		
		ChargesRule chargesRule = new ChargesRule("1234"
				,  ChargesBasis.PERCENTAGE
				, BigDecimal.ONE
				, ChargesType.CHARGE
				, "XYZ"
				, "1"
				, "123"
				, "1234"
				, "12345"
				, "not same stateCode"
				, AttachmentLevel.SECTION_LEVEL
				, RoundRuleType.NORMAL
				, "1 + 1");		
		
		
		boolean actual = aService.verifyAttachLevelCodeMatchOf(chargesRule
				, new MockPremium().createPremium());
		assertFalse(actual);
	}
	
	
	@Test
	public void testCoverageLevelMatchesWhichReturnsTrue() {
		AttachmentLevelCodeMatcherService aService = new AttachmentLevelCodeMatcherService();
		
		ChargesRule chargesRule = new ChargesRule("1234"
				,  ChargesBasis.PERCENTAGE
				, BigDecimal.ONE
				, ChargesType.CHARGE
				, "XYZ"
				, "1"
				, "123"
				, "1234"
				, "12345"
				, "stateCode"
				, AttachmentLevel.COVERAGE_LEVEL
				, RoundRuleType.NORMAL
				, "1 + 1");		
		
		
		boolean actual = aService.verifyAttachLevelCodeMatchOf(chargesRule
				, new MockPremium().createPremium());
		assertTrue(actual);
	}	
	
	@Test
	public void testCoverageLevel_CoverageCodeNotMatch_WhichReturnsFalse() {
		AttachmentLevelCodeMatcherService aService = new AttachmentLevelCodeMatcherService();
		
		ChargesRule chargesRule = new ChargesRule("1234"
				,  ChargesBasis.PERCENTAGE
				, BigDecimal.ONE
				, ChargesType.CHARGE
				, "XYZ"
				, "1"
				, "123"
				, "1234"
				, "coverage not equal to 12345"
				, "stateCode"
				, AttachmentLevel.COVERAGE_LEVEL
				, RoundRuleType.NORMAL
				, "1 + 1");		
		
		
		boolean actual = aService.verifyAttachLevelCodeMatchOf(chargesRule
				, new MockPremium().createPremium());
		assertFalse(actual);
	}		
	class MockPremium {
		public Premium createPremium() {
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
			
			return premium;
		}
	}
}
