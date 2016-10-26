package com.leqcar.instalmentbilling.infixpostfix;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.leqcar.instalmentbilling.infrastructure.infixpostfix.InfixToPostfix;

public class InfixToPostfixTest {

	@Test
	public void testTransform() {
		
        InfixToPostfix ut = InfixToPostfix.INSTANCE;

        assertEquals("", ut.apply(""));
        assertEquals("2 2 * 3 +", ut.apply("2 * 2 + 3"));
        assertEquals("2 2 3 + *", ut.apply("2 * (2 + 3)"));        
        assertEquals("30.1 5 6 * + 7 8 5 + * -", ut.apply("30.1 + 5 * 6 - 7 * ( 8 + 5 )"));
        assertEquals("30.1 5 sin 6 * + 7 8 5 + * -", ut.apply("30.1 + sin 5 * 6 - 7 * ( 8 + 5 )"));
       
	}
}
