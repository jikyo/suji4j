package com.github.jikyo.suji;

import junit.framework.TestCase;

import java.math.BigDecimal;

public class NumeralTest extends TestCase {

    public void testToString() {
        Numeral n = new Numeral(BigDecimal.ONE, 2, 3);
        assertEquals("{val: 1, begin: 2, end: 3}", n.toString());
    }

    public void testEquals() {
        Numeral a = new Numeral(BigDecimal.ONE, 2, 3);
        Numeral b = new Numeral(BigDecimal.ONE, 2, 3);
        
        assertFalse(a.equals(null));
        assertFalse(a.equals("NumeralTest.testEquals()"));
        assertFalse(a.equals(new Numeral(BigDecimal.ZERO, 2, 3)));
        assertFalse(a.equals(new Numeral(BigDecimal.ONE, 0, 3)));
        assertFalse(a.equals(new Numeral(BigDecimal.ONE, 2, 0)));
        assertTrue(a.equals(a));
        assertTrue(a.equals(b));
    }

}
