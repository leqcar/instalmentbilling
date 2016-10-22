package com.leqcar.instalmentbilling.infixpostfix;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Supplier;

public final class OperatorFactoryResolver implements Function<String, OperatorFactory<BigDecimal>> {
    private static final Function<String, OperatorFactory<BigDecimal>> INSTANCE = new OperatorFactoryResolver();

    private Map<String, OperatorFactory<BigDecimal>> operatorFactories;

    private OperatorFactoryResolver() {
        super();
        this.operatorFactories = createOperatorFactories();
    }

    @Override
    public OperatorFactory<BigDecimal> apply(String key) {
        return operatorFactories.get(key);
    }

    public static Function<String, OperatorFactory<BigDecimal>> getInstance() {
        return INSTANCE;
    }

    private Map<String, OperatorFactory<BigDecimal>> createOperatorFactories() {
        Map<String, OperatorFactory<BigDecimal>> factories = new TreeMap<>();
        addOperators(factories);
        addMathFunctions(factories);
        return Collections.unmodifiableMap(factories);
    }

    public void addOperators(Map<String, OperatorFactory<BigDecimal>> factories) {
    	
    	Arrays.asList(Operator.values()).stream()
    		.forEach(operator -> factories.put(operator.getSymbol(), stack -> {
    			 OperatorExpression expression = new OperatorExpression();
                 expression.setOperator(operator);
                 expression.setB(stack.pop());
                 expression.setA(stack.pop());  
                 return expression;
    		}));
/*    		    	
        for (final Operator operator : Operator.values()) {
            factories.put(operator.getSymbol(), new OperatorFactory<BigDecimal>() {
                @Override
                public Supplier<BigDecimal> apply(Stack<Supplier<BigDecimal>> stack) {
                    OperatorExpression expression = new OperatorExpression();
                    expression.setOperator(operator);
                    expression.setB(stack.pop());
                    expression.setA(stack.pop());
                    return expression;
                }
            });
        }*/
    }

    public void addMathFunctions(Map<String, OperatorFactory<BigDecimal>> factories) {
    	
    	Maps.getStaticMathFunctions().values().stream()
    		.forEach(method -> {
    			if (method.getParameters().length == 1) {
    				 factories.put(method.getName(), getUnaryOperatorFactory(method));
    			} else if (method.getParameters().length == 2) {
    				 factories.put(method.getName(), getBinaryOperatorFactory(method));
    			}
    		});    	
    	
/*        for (Method method : Maps.getStaticMathFunctions().values()) {
            switch (method.getParameters().length) {
            case 1:
                factories.put(method.getName(), getUnaryOperatorFactory(method));
                break;
            case 2:
                factories.put(method.getName(), getBinaryOperatorFactory(method));
                break;
            }
        }*/
    }

    private OperatorFactory<BigDecimal> getUnaryOperatorFactory(final Method method) {
    	return stack -> {
            UnaryExpression<BigDecimal> expression = new UnaryExpression<>();
            expression.setMethod(method);
            expression.setOperand(stack.pop());
            return expression;    		
    	};
/*    	
        return new OperatorFactory<BigDecimal>() {			
			@Override
			public Supplier<BigDecimal> apply(Stack<Supplier<BigDecimal>> t) {
                UnaryExpression<BigDecimal> expression = new UnaryExpression<>();
                expression.setMethod(method);
                expression.setOperand(t.pop());
                return expression;
			}
		};    */    	
    }

    private OperatorFactory<BigDecimal> getBinaryOperatorFactory(final Method method) {
        return new OperatorFactory<BigDecimal>() {
			
			@Override
			public Supplier<BigDecimal> apply(Stack<Supplier<BigDecimal>> t) {
                BinaryExpression<BigDecimal> expression = new BinaryExpression<>();
                expression.setMethod(method);
                expression.setB(t.pop());
                expression.setA(t.pop());
                return expression;
			}
		};
    }

    @Override
    public String toString() {
        return operatorFactories.keySet().toString();
    }
}