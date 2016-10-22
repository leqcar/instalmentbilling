package com.leqcar.instalmentbilling.infixpostfix;

import java.util.function.Supplier;

public final class ConstantExpression<T> implements Supplier<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }
}