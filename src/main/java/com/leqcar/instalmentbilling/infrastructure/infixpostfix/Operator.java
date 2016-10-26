package com.leqcar.instalmentbilling.infrastructure.infixpostfix;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public enum Operator {
    EXPONENT("^") {
        @Override
        public BigDecimal evaluate(BigDecimal a, BigDecimal b) {
            return a.pow(b.toBigInteger().intValue());
        }
    },
    MULTIPLY("*") {
        @Override
        public BigDecimal evaluate(BigDecimal a, BigDecimal b) {
            return a.multiply(b);
        }
    },
    DIVIDE("/") {
        @Override
        public BigDecimal evaluate(BigDecimal a, BigDecimal b) {
            return a.divide(b);
        }
    },
    ADD("+") {
        @Override
        public BigDecimal evaluate(BigDecimal a, BigDecimal b) {
            return a.add(b);
        }
    },
    SUBTRACT("-") {
        @Override
        public BigDecimal evaluate(BigDecimal a, BigDecimal b) {
            return a.subtract(b);
        }
    };

    private static final Map<String, Operator> REGISTRY = new HashMap<String, Operator>();
    static {
        for (Operator operator : values()) {
            REGISTRY.put(operator.getSymbol(), operator);
        }
    }

    private String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean hasPrecedence(Operator operator) {
        return operator != null && compareTo(operator) > 0;
    }

    public static boolean contains(String symbol) {
        return REGISTRY.containsKey(symbol);
    }

    public static Operator get(String symbol) {
        return REGISTRY.get(symbol);
    }

    public abstract BigDecimal evaluate(BigDecimal a, BigDecimal b);
}
