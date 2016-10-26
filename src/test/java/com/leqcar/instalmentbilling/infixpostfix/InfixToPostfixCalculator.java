package com.leqcar.instalmentbilling.infixpostfix;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

import com.leqcar.instalmentbilling.infrastructure.infixpostfix.Collector;
import com.leqcar.instalmentbilling.infrastructure.infixpostfix.Constant;
import com.leqcar.instalmentbilling.infrastructure.infixpostfix.ExpressionFactory;
import com.leqcar.instalmentbilling.infrastructure.infixpostfix.InfixToPostfix;
import com.leqcar.instalmentbilling.infrastructure.infixpostfix.Operand;
import com.leqcar.instalmentbilling.infrastructure.infixpostfix.OperandCollector;
import com.leqcar.instalmentbilling.infrastructure.infixpostfix.OperatorFactoryResolver;

public class InfixToPostfixCalculator {
    public static void main(String... args) {
/*        System.out.printf("Operators: %s\n", OperatorFactoryResolver.getInstance());
        System.out.printf("Constants: %s\n", Arrays.asList(Constant.values()));*/

        Scanner console = new Scanner(System.in);
        System.out.print("Please enter an expression: ");
        String infix = console.nextLine();
        String postfix = InfixToPostfix.INSTANCE.apply(infix);
        System.out.printf("Infix  : '%s'\n", infix);
        System.out.printf("Postfix: '%s'\n", postfix);

        Collector<String, Supplier<BigDecimal>> variables = new OperandCollector<BigDecimal>();
        Function<String, Supplier<BigDecimal>> factory = initExpressionFactory(variables);
        Supplier<BigDecimal> expression = factory.apply(postfix);
        if (variables.get().isEmpty()) {
            System.out.printf("The result of the expression is: %f\n", expression.get());
        } else {
            do {
                System.out.println(variables);
                for (Supplier<BigDecimal> variable : variables.get()) {
                    if (variable instanceof Operand) {
                        Operand<BigDecimal> operand = (Operand<BigDecimal>) variable;
                        System.out.printf("Please enter the value of %s: ", operand.getName());
                        operand.setValue(new BigDecimal(console.nextLine()));
                    }
                }
                System.out.println(variables);
                System.out.printf("The result of the expression is: %f\n", expression.get());
                System.out.print("Enter 'y' if you want to repeat: ");
            } while ("y".equals(console.nextLine()));
        }
        System.out.print("Thank you and goodbye.");
        console.close();
/*        XMLEncoder encoder = new XMLEncoder(System.out);
        encoder.writeObject(expression);
        encoder.close();*/
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
