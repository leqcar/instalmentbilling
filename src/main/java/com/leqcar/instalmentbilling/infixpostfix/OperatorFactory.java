package com.leqcar.instalmentbilling.infixpostfix;

import java.util.Stack;
import java.util.function.Function;
import java.util.function.Supplier;

public interface OperatorFactory<T> extends Function<Stack<Supplier<T>>, Supplier<T>> {

}
