package com.leqcar.instalmentbilling.infixpostfix;

import java.math.BigDecimal;
import java.util.function.Supplier;

public final class OperatorExpression implements Supplier<BigDecimal> {
    private Operator operator;
    private Supplier<BigDecimal> a;
    private Supplier<BigDecimal> b;

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Supplier<BigDecimal> getA() {
        return a;
    }

    public void setA(Supplier<BigDecimal> a) {
        this.a = a;
    }

    public Supplier<BigDecimal> getB() {
        return b;
    }

    public void setB(Supplier<BigDecimal> b) {
        this.b = b;
    }

    @Override
    public BigDecimal get() {
        return operator.evaluate(b.get(), a.get());
    }
}