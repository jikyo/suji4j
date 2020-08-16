package com.github.jikyo.suji;

import junit.framework.TestCase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class CharTest extends TestCase {

    public void testConstructor() throws Exception {
        try {
            Constructor<Char> constructor = Char.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
            fail("CharTest.testConstructor(): after newInstance()");
        } catch (InvocationTargetException e) {
            assertNotNull("success");
        } catch (Exception e) {
            fail("CharTest.testConstructor(): not InvocationTargetException");
        }
    }
}
