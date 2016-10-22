package com.leqcar.instalmentbilling.infixpostfix;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class OperandCollector<T> implements Collector<String, Supplier<T>> {
    private Map<String, Supplier<T>> operands = new TreeMap<String, Supplier<T>>();

    @Override
    public Collection<Supplier<T>> get() {
        return Collections.unmodifiableCollection(operands.values());
    }

    @Override
    public Supplier<T> add(String key) {
        Supplier<T> expression = operands.get(key);
        if (expression == null) {
            Operand<T> operand = new Operand<T>();
            operand.setName(key);
            operands.put(key, operand);
            expression = operand;
        }
        return expression;
    }

    @Override
    public String toString() {
        return operands.values().toString();
    }
}
