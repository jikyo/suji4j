package com.github.jikyo.suji;

import junit.framework.TestCase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

public class KansujinizerTest extends TestCase {

    public void testConstructor() throws Exception {
        try {
            Constructor<Kansujinizer> constructor = Kansujinizer.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
            fail("KansujinizerTest.testConstructor(): after newInstance()");
        } catch (InvocationTargetException e) {
            assertNotNull("success");
        } catch (Exception e) {
            fail("KansujinizerTest.testConstructor(): not InvocationTargetException");
        }
    }

    public void testKansuji() {
        assertEquals("零", Kansujinizer.kansuji(BigDecimal.valueOf(0), false));
        assertEquals("三十", Kansujinizer.kansuji(BigDecimal.valueOf(30), false));
        assertEquals("五十六", Kansujinizer.kansuji(BigDecimal.valueOf(56), false));
        assertEquals("百", Kansujinizer.kansuji(BigDecimal.valueOf(100), false));
        assertEquals("一百", Kansujinizer.kansuji(BigDecimal.valueOf(100), true));
        assertEquals("百十一", Kansujinizer.kansuji(BigDecimal.valueOf(111), false));
        assertEquals("一千四", Kansujinizer.kansuji(BigDecimal.valueOf(1004), true));
        assertEquals("千四", Kansujinizer.kansuji(BigDecimal.valueOf(1004), false));
        assertEquals("一万五", Kansujinizer.kansuji(BigDecimal.valueOf(10005), true));
        assertEquals("万五", Kansujinizer.kansuji(BigDecimal.valueOf(10005), false));
        assertEquals("二千万五", Kansujinizer.kansuji(BigDecimal.valueOf(20000005), false));
        assertEquals("一千万", Kansujinizer.kansuji(BigDecimal.valueOf(10000000), true));
        assertEquals("千万", Kansujinizer.kansuji(BigDecimal.valueOf(10000000), false));
        assertEquals("二千一万三百", Kansujinizer.kansuji(BigDecimal.valueOf(20010300), false));
        assertEquals("二十億六百七", Kansujinizer.kansuji(BigDecimal.valueOf(2000000607), false));
        assertEquals("三十二", Kansujinizer.kansuji(BigDecimal.valueOf(32.001), false));
    }

    public void testMinus() {
        assertEquals("マイナス百", Kansujinizer.kansuji(BigDecimal.valueOf(-100), false));
        assertEquals("マイナス一百", Kansujinizer.kansuji(BigDecimal.valueOf(-100), true));
        assertEquals("マイナス二千一万三百", Kansujinizer.kansuji(BigDecimal.valueOf(-20010300), true));
        assertEquals("零", Kansujinizer.kansuji(BigDecimal.valueOf(-0), true));
        assertEquals("マイナス一", Kansujinizer.kansuji(BigDecimal.valueOf(-1), true));
        assertEquals("マイナス十億", Kansujinizer.kansuji(BigDecimal.valueOf(-1000000000), false));
        assertEquals("マイナス一十億", Kansujinizer.kansuji(BigDecimal.valueOf(-1000000000), true));
    }

}
