package com.github.jikyo.suji;

import junit.framework.TestCase;

public class AccumulatorTest extends TestCase {

    public void testToString() throws Exception {
        Accumulator acc = new Accumulator();
        String expected =  "acc:\n"
            + "\tinside: false\n"
            + "\tbeg: -1\n"
            + "\tend: -1\n"
            + "\tval: 0\n"
            + "\tvalCardinal: 0\n"
            + "\tlastCardinal: 10\n"
            + "\tbase: 10\n";
        assertEquals(expected, acc.toString());
    }

    public void testIncrement() throws Exception {
        Accumulator acc = new Accumulator();
        acc.increment(5);
        assertFalse(acc.inside());
    }
}
