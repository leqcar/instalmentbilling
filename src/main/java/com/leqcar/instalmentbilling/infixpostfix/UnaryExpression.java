package com.leqcar.instalmentbilling.infixpostfix;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public final class UnaryExpression<T> implements Supplier<T> {
    private Method method;
    private Supplier<T> operand;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Supplier<T> getOperand() {
        return operand;
    }

    public void setOperand(Supplier<T> operand) {
        this.operand = operand;
    }

    @SuppressWarnings("unchecked")
	@Override
    public T get() {
        try {
            return (T) method.invoke(null, operand.get());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ParseException(e);
        }
    }
}