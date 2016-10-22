package com.leqcar.instalmentbilling.infixpostfix;

import java.math.BigDecimal;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Supplier;

public class ExpressionFactory implements Function<String, Supplier<BigDecimal>> {
    private Function<String, OperatorFactory<BigDecimal>> operatorFactories;
    private Function<String, Supplier<BigDecimal>> constants;
    private Collector<String, Supplier<BigDecimal>> variables;

    @Override
    public Supplier<BigDecimal> apply(String expression) {
        Stack<Supplier<BigDecimal>> stack = new Stack<>();
        CharacterIterator chars = new StringCharacterIterator(expression);
        while (chars.current() != CharacterIterator.DONE) {
            if (Character.isWhitespace(chars.current())) {
                chars.next();
                continue;
            }
            if (Character.isDigit(chars.current())) {
                stack.push(parseNumber(chars));
            } else if (Character.isLetter(chars.current())) {
                stack.push(parseNamedOperand(chars, stack));
            } else {
                stack.push(parseSymbolOperator(chars, stack));
            }
        }
        return stack.pop();
    }

    public void setOperatorFactories(Function<String, OperatorFactory<BigDecimal>> operatorFactories) {
        this.operatorFactories = operatorFactories;
    }

    public void setConstants(Function<String, Supplier<BigDecimal>> constants) {
        this.constants = constants;
    }

    public void setVariables(Collector<String, Supplier<BigDecimal>> variables) {
        this.variables = variables;
    }

    private Supplier<BigDecimal> parseNamedOperand(CharacterIterator chars
    		, Stack<Supplier<BigDecimal>> stack) {
        String name = parseName(chars);
        Supplier<BigDecimal> operand = constants.apply(name);
        if (operand != null) {
            return operand;
        }
        OperatorFactory<BigDecimal> factory = operatorFactories.apply(name);
        if (factory != null) {
            return factory.apply(stack);
        }
        return variables.add(name);
    }

    private Supplier<BigDecimal> parseSymbolOperator(CharacterIterator chars, Stack<Supplier<BigDecimal>> stack) {
        String symbol = parseSymbol(chars);
        OperatorFactory<BigDecimal> factory = operatorFactories.apply(symbol);
        if (factory != null) {
            return factory.apply(stack);
        }
        throw new ParseException(String.format("Unknown symbol '%s'", symbol));
    }

    private String parseName(CharacterIterator chars) {
        StringBuilder builder = new StringBuilder();
        for (char ch = chars.current(); ch != CharacterIterator.DONE && Character.isLetterOrDigit(ch); ch =
            chars.next()) {
            builder.append(ch);
        }
        return builder.toString();
    }

    private Supplier<BigDecimal> parseNumber(CharacterIterator chars) {
        StringBuilder builder = new StringBuilder();
        for (char ch = chars.current(); ch != CharacterIterator.DONE && (Character.isDigit(ch) || ch == '.'); ch =
            chars.next()) {
            builder.append(ch);
        }
        ConstantExpression<BigDecimal> number = new ConstantExpression<BigDecimal>();
        number.setValue(new BigDecimal(builder.toString()));
        return number;
    }

    private String parseSymbol(CharacterIterator chars) {
        String operator = String.valueOf(chars.current());
        chars.next();
        return operator;
    }
}