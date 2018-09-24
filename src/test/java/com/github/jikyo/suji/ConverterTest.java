package com.github.jikyo.suji;

import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConverterTest extends TestCase {

    public void testValuesInt() {
        List<Numeral> expect;

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(5), 0, 1));
        assertEquals(expect, Converter.values("５"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(5), 3, 4));
        assertEquals(expect, Converter.values("これは5円です。"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(1000), 3, 8));
        assertEquals(expect, Converter.values("これは1,000円です。"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(2), 2, 3));
        assertEquals(expect, Converter.values("値は二"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(3000), 2, 7));
        assertEquals(expect, Converter.values("値は3,000"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(400), 0, 4));
        assertEquals(expect, Converter.values("400, 円になります。"));

        expect = Arrays.asList(
                Numeral.valueOf(BigDecimal.valueOf(1000), 0, 6),
                Numeral.valueOf(BigDecimal.valueOf(2000), 7, 13),
                Numeral.valueOf(BigDecimal.valueOf(3000), 14, 20)
        );
        assertEquals(expect, Converter.values("1,000, 2,000, 3,000,"));

        expect = Arrays.asList(
                Numeral.valueOf(BigDecimal.valueOf(5), 3, 4),
                Numeral.valueOf(BigDecimal.valueOf(60), 5, 7),
                Numeral.valueOf(BigDecimal.valueOf(700), 8, 11)
        );
        assertEquals(expect, Converter.values("数列、5、60、700、"));
    }

    public void testValuesDecimal() {
        List<Numeral> expect;

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(0.6), 0, 3));
        assertEquals(expect, Converter.values("0.6"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(0.0007), 0, 6));
        assertEquals(expect, Converter.values("0.0007"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(0.987654321), 0, 11));
        assertEquals(expect, Converter.values("0.987654321"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(1.3), 0, 3));
        assertEquals(expect, Converter.values("1.3"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(123.45), 0, 6));
        assertEquals(expect, Converter.values("123.45"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(1), 0, 2));
        assertEquals(expect, Converter.values("１."));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(1), 3, 5));
        assertEquals(expect, Converter.values("これは1.です。"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(0.325), 0, 6));
        assertEquals(expect, Converter.values("三割二分五厘"));

        expect = Arrays.asList(
                Numeral.valueOf(BigDecimal.valueOf(2.3), 6, 9),
                Numeral.valueOf(BigDecimal.valueOf(0.005), 10, 12)
        );
        assertEquals(expect, Converter.values("表記間違いの三割二部五厘です。"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(0.123), 2, 6));
        assertEquals(expect, Converter.values("多分.123です。"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(0.00123), 4, 10));
        assertEquals(expect, Converter.values("おそらく.00123です。"));
    }

    public void testValuesKansuji() {
        List<Numeral> expect;

        expect = Arrays.asList(
                Numeral.valueOf(BigDecimal.valueOf(1), 0, 1),
                Numeral.valueOf(BigDecimal.valueOf(2000000305017L), 6, 15)
        );
        assertEquals(expect, Converter.values("１つの価格が二兆30万五千十7円になります。"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(250), 0, 4));
        assertEquals(expect, Converter.values("二百五十円"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(1007), 0, 2));
        assertEquals(expect, Converter.values("千七円"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(120052), 3, 9));
        assertEquals(expect, Converter.values("価格は十二万五十二円になります。"));


        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(12000000000052L), 3, 9));
        assertEquals(expect, Converter.values("価格は十二兆五十二"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(1001000000000052L), 0, 6));
        assertEquals(expect, Converter.values("千一兆五十二円になります。"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(604002005), 0, 9));
        assertEquals(expect, Converter.values("6億400万2千5になります。"));

        expect = Collections.singletonList(Numeral.valueOf(BigDecimal.valueOf(11010), 3, 6));
        assertEquals(expect, Converter.values("それは万千十です。"));
    }

    public void sample() {
        String src = "１つの価格が二兆30万五千十7円になります。";
        List<Numeral> results = Converter.values(src);
        System.out.println(results); // [{val: 1, begin: 0, end: 1}, {val: 2000000305017, begin: 6, end: 15}]
        Numeral n = results.get(1);
        System.out.println(n.value()); // 2000000305017
        System.out.println(src.substring(n.begin(), n.end())); // 二兆30万五千十7
    }
}
