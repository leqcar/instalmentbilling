package com.leqcar.instalmentbilling.infrastructure.infixpostfix;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class Maps {
    private static Map<String, Method> mathFunctions;

    public static <K, V> Function<K, V> asResolver(final Map<K, V> map) {
    	return t -> map.get(t);
    }

    public  static Map<String, Method> getStaticMathFunctions() {
        if (mathFunctions == null) {
            Map<String, Method> map = new HashMap<String, Method>();
            for (Method method : Math.class.getMethods()) {
                if (isPublicStaticDoubleFunction(method) && hasDoubleParameters(method)) {
                    map.put(method.getName(), method);
                }
            }
            mathFunctions = map;
        }
        return Collections.unmodifiableMap(mathFunctions);
    }

    private static boolean isPublicStaticDoubleFunction(Method method) {
        return Modifier.isStatic(method.getModifiers()) //
            && Modifier.isPublic(method.getModifiers()) //
            && double.class.equals(method.getReturnType());
    }

    private static boolean hasDoubleParameters(Method method) {
        for (Class<?> type : method.getParameterTypes()) {
            if (!double.class.equals(type)) {
                return false;
            }
        }
        return method.getParameters().length > 0;
    }
}
