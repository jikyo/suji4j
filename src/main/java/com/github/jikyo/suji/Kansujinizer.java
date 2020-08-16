package com.github.jikyo.suji;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Kansujinizer {

    private static final String zero = "零";

    private static final String minus = "マイナス";

    @SuppressWarnings("serial")
    private static final Map<BigInteger, String> number = Collections.unmodifiableMap(new HashMap<BigInteger, String>() {{
        put(BigInteger.valueOf(1), "一");
        put(BigInteger.valueOf(2), "二");
        put(BigInteger.valueOf(3), "三");
        put(BigInteger.valueOf(4), "四");
        put(BigInteger.valueOf(5), "五");
        put(BigInteger.valueOf(6), "六");
        put(BigInteger.valueOf(7), "七");
        put(BigInteger.valueOf(8), "八");
        put(BigInteger.valueOf(9), "九");
    }} );

    private static final BigInteger[] radic = {
            BigInteger.valueOf(10000000000000000L),
            BigInteger.valueOf(1000000000000L),
            BigInteger.valueOf(100000000),
            BigInteger.valueOf(10000),
            BigInteger.valueOf(1000),
            BigInteger.valueOf(100),
            BigInteger.valueOf(10),
    };

    private static final String[] radic_kanji = {
            "京",
            "兆",
            "億",
            "万",
            "千",
            "百",
            "十",
    };

    private Kansujinizer() { throw new AssertionError(); }

    private static String string(BigInteger v, int index, boolean one) {
        int compare = v.compareTo(BigInteger.ZERO);
        if (compare == 0) {
            return "";
        }
        if (compare == -1) {
            return Kansujinizer.minus + string(v.abs(), 0, one);
        }
        if (Kansujinizer.number.containsKey(v)) {
            return Kansujinizer.number.get(v);
        }
        if (Kansujinizer.radic.length <= index) {
            return "";
        }

        BigInteger div = v.divide(Kansujinizer.radic[index]);
        if (div.compareTo(BigInteger.ZERO) == 0) {
            return Kansujinizer.string(v, index + 1, one);
        }

        String prefix = "";
        if (div.compareTo(BigInteger.ONE) != 0) {
            prefix = Kansujinizer.string(div, index + 1, one);
        } else if (one) {
            prefix = Kansujinizer.number.get(BigInteger.valueOf(1));
        }

        BigInteger mod = v.mod(Kansujinizer.radic[index]);
        return prefix
                + Kansujinizer.radic_kanji[index]
                + Kansujinizer.string(mod, index + 1, one);
    }

    static String kansuji(BigDecimal v, boolean one) {
        BigInteger i = v.toBigInteger();
        if (i.compareTo(BigInteger.ZERO) == 0) {
            return Kansujinizer.zero;
        }
        return Kansujinizer.string(i, 0, one);
    }

}
