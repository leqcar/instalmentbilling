package com.leqcar.instalmentbilling.infixpostfix;

import java.util.function.Supplier;

public class Operand<T> implements Supplier<T> {
    private String name;
    private T value;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }


    @Override
    public String toString() {
        return name + "=" + value;
    }

	@Override
	public T get() {
		return value;
	}
}
