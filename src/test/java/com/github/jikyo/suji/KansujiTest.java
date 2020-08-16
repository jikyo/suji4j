package com.github.jikyo.suji;

import junit.framework.TestCase;

public class KansujiTest extends TestCase {

    public void testToString() {
        Kansuji k = new Kansuji("一", 2, 3);
        assertEquals("{val: 一, begin: 2, end: 3}", k.toString());
    }

    public void testEquals() {
        Kansuji a = new Kansuji("一", 2, 3);
        Kansuji b = new Kansuji("一", 2, 3);

        assertFalse(a.equals(null));
        assertFalse(a.equals("Kansujiest.testEquals()"));
        assertFalse(a.equals(new Kansuji("二", 2, 3)));
        assertFalse(a.equals(new Kansuji("一", 0, 3)));
        assertFalse(a.equals(new Kansuji("一", 2, 0)));
        assertTrue(a.equals(a));
        assertTrue(a.equals(b));
    }

}
