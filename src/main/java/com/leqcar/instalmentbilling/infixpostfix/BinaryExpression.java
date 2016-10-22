package com.leqcar.instalmentbilling.infixpostfix;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public final class BinaryExpression<T> implements Supplier<T> {
    private Method method;
    private Supplier<T> b;
    private Supplier<T> a;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Supplier<T> getB() {
        return b;
    }

    public void setB(Supplier<T> b) {
        this.b = b;
    }

    public Supplier<T> getA() {
        return a;
    }

    public void setA(Supplier<T> a) {
        this.a = a;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get() {
        try {
            return (T) method.invoke(null, b.get(), a.get());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ParseException(e);
        }
    }
}