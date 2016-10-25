package com.leqcar.instalmentbilling.domain.model.charges;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;

public enum RoundRuleType implements BiFunction<Integer, BigDecimal, BigDecimal>{

	UP {
		@Override
		public BigDecimal apply(Integer roundPrecision, BigDecimal amount) {
			return amount.setScale(roundPrecision, RoundingMode.UP);
		}		
	}, 
	
	DOWN {

		@Override
		public BigDecimal apply(Integer roundPrecision, BigDecimal amount) {
			return amount.setScale(roundPrecision, RoundingMode.DOWN);
		}		
	}, 
	
	NORMAL {

		@Override
		public BigDecimal apply(Integer roundPrecision, BigDecimal amount) {	
			return amount.setScale(roundPrecision, RoundingMode.HALF_UP);
		}		

	};

}
