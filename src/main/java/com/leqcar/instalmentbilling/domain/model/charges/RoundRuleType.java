package com.leqcar.instalmentbilling.domain.model.charges;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public enum RoundRuleType implements Function<BigDecimal, BigDecimal>{

	UP {
		@Override
		public BigDecimal apply(BigDecimal t) {
			return null;
		}
		
	}, 
	
	DOWN {
		@Override
		public BigDecimal apply(BigDecimal t) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}, 
	
	NORMAL {
		@Override
		public BigDecimal apply(BigDecimal t) {
			return t.setScale(5, RoundingMode.HALF_UP);
		}
		
	};
}
