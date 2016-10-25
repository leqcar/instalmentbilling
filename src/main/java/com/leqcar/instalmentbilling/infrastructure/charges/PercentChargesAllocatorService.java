package com.leqcar.instalmentbilling.infrastructure.charges;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import com.leqcar.instalmentbilling.domain.model.charges.Charges;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesAllocatorService;
import com.leqcar.instalmentbilling.domain.model.charges.ChargesRule;
import com.leqcar.instalmentbilling.domain.model.policy.Policy;
import com.leqcar.instalmentbilling.domain.model.product.Premium;
import com.leqcar.instalmentbilling.domain.service.AttachmentLevelCodeMatcherService;
import com.leqcar.instalmentbilling.infixpostfix.Collector;
import com.leqcar.instalmentbilling.infixpostfix.Constant;
import com.leqcar.instalmentbilling.infixpostfix.ExpressionFactory;
import com.leqcar.instalmentbilling.infixpostfix.InfixToPostfix;
import com.leqcar.instalmentbilling.infixpostfix.OperandCollector;
import com.leqcar.instalmentbilling.infixpostfix.OperatorFactoryResolver;

public class PercentChargesAllocatorService implements ChargesAllocatorService {
	
	private Function<String, Supplier<BigDecimal>> expressionFactory = initExpressionFactory( new OperandCollector<BigDecimal>());

	@Override
	public void allocateCharges(Policy aPolicy, ChargesRule chargesRule) {
		AttachmentLevelCodeMatcherService attachmentLevelCodeMatcherService = new AttachmentLevelCodeMatcherService();
		
		Integer roundPrecision = aPolicy.getProduct().getRoundPrecision().getValue();
		List<Premium> policyPremiums = aPolicy.getProduct().getPolicyPremiums();
		
		for (Premium premium : policyPremiums) {
			BigDecimal calculationResult = BigDecimal.ZERO;
			if (attachmentLevelCodeMatcherService.verifyAttachLevelCodeMatchOf(chargesRule, premium)) {
				String postfix = InfixToPostfix.INSTANCE.apply(chargesRule.getCalculationFormula());
				calculationResult = expressionFactory.apply(postfix).get();				
			} else {
				continue;
			}
						
			Charges charges = new Charges(chargesRule.getChargeCode(), 
					chargesRule.getChargeBasis().name(), 
					chargesRule.getChargeType().name(), 
					chargesRule.getChargeCount(),
					BigDecimal.valueOf(0.0), 					
					BigDecimal.valueOf(0.0), 
					chargesRule.applyRounding(roundPrecision, calculationResult), 
					BigDecimal.valueOf(0.0));
			
			premium.addCharges(charges);
		}
	}

    private static Function<String, Supplier<BigDecimal>> initExpressionFactory(
        Collector<String, Supplier<BigDecimal>> variables) {
        ExpressionFactory factory = new ExpressionFactory();
        factory.setConstants(Constant.RESOLVER);
        factory.setOperatorFactories(OperatorFactoryResolver.getInstance());
        factory.setVariables(variables);
        return factory;
    }	
}

