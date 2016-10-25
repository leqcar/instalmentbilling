package com.leqcar.instalmentbilling.infixpostfix;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Supplier;

public enum Constant implements Supplier<BigDecimal> {
    PI {
        @Override
        public BigDecimal get() {
            return BigDecimal.valueOf(Math.PI);
        }
    },    
	random {
        @Override
        public BigDecimal get() {
            return BigDecimal.valueOf(Math.random());            
        }
    };
    
	private static final Map<String, Supplier<BigDecimal>> REGISTRY = new TreeMap<String, Supplier<BigDecimal>>();
    static {
    	if (REGISTRY.size() == 0) {
            for (Constant constant : values()) {
                REGISTRY.put(constant.name(), constant);
            }    		
    	}
    }
    public static final Function<String, Supplier<BigDecimal>> RESOLVER = Maps.asResolver(REGISTRY);

    @Override
    public String toString() {
        return name() + "=" + get();
    } 
}
