package com.github.jikyo.suji;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Char {

    @SuppressWarnings("serial")
    private static final Map<Character, BigDecimal> NUMBER = Collections.unmodifiableMap(new HashMap<Character, BigDecimal>() {{
        put('0',  BigDecimal.valueOf(0));
        put('1',  BigDecimal.valueOf(1));
        put('2',  BigDecimal.valueOf(2));
        put('3',  BigDecimal.valueOf(3));
        put('4',  BigDecimal.valueOf(4));
        put('5',  BigDecimal.valueOf(5));
        put('6',  BigDecimal.valueOf(6));
        put('7',  BigDecimal.valueOf(7));
        put('8',  BigDecimal.valueOf(8));
        put('9',  BigDecimal.valueOf(9));
        put('０', BigDecimal.valueOf(0));
        put('１', BigDecimal.valueOf(1));
        put('２', BigDecimal.valueOf(2));
        put('３', BigDecimal.valueOf(3));
        put('４', BigDecimal.valueOf(4));
        put('５', BigDecimal.valueOf(5));
        put('６', BigDecimal.valueOf(6));
        put('７', BigDecimal.valueOf(7));
        put('８', BigDecimal.valueOf(8));
        put('９', BigDecimal.valueOf(9));
        put('〇', BigDecimal.valueOf(0));
        put('一', BigDecimal.valueOf(1));
        put('二', BigDecimal.valueOf(2));
        put('三', BigDecimal.valueOf(3));
        put('四', BigDecimal.valueOf(4));
        put('五', BigDecimal.valueOf(5));
        put('六', BigDecimal.valueOf(6));
        put('七', BigDecimal.valueOf(7));
        put('八', BigDecimal.valueOf(8));
        put('九', BigDecimal.valueOf(9));
        put('零', BigDecimal.valueOf(0));
        put('壱', BigDecimal.valueOf(1));
        put('弐', BigDecimal.valueOf(2));
        put('参', BigDecimal.valueOf(3));
        put('肆', BigDecimal.valueOf(4));
        put('伍', BigDecimal.valueOf(5));
        put('陸', BigDecimal.valueOf(6));
        put('漆', BigDecimal.valueOf(7));
        put('捌', BigDecimal.valueOf(8));
        put('玖', BigDecimal.valueOf(9));
        put('Ⅰ', BigDecimal.valueOf(1));
        put('Ⅱ', BigDecimal.valueOf(2));
        put('Ⅲ', BigDecimal.valueOf(3));
        put('Ⅳ', BigDecimal.valueOf(4));
        put('Ⅴ', BigDecimal.valueOf(5));
        put('Ⅵ', BigDecimal.valueOf(6));
        put('Ⅶ', BigDecimal.valueOf(7));
        put('Ⅷ', BigDecimal.valueOf(8));
        put('Ⅸ', BigDecimal.valueOf(9));
        put('Ⅹ', BigDecimal.valueOf(10));
        put('Ⅺ', BigDecimal.valueOf(11));
        put('Ⅻ', BigDecimal.valueOf(12));
        put('ⅰ', BigDecimal.valueOf(1));
        put('ⅱ', BigDecimal.valueOf(2));
        put('ⅲ', BigDecimal.valueOf(3));
        put('ⅳ', BigDecimal.valueOf(4));
        put('ⅴ', BigDecimal.valueOf(5));
        put('ⅵ', BigDecimal.valueOf(6));
        put('ⅶ', BigDecimal.valueOf(7));
        put('ⅷ', BigDecimal.valueOf(8));
        put('ⅸ', BigDecimal.valueOf(9));
        put('ⅹ', BigDecimal.valueOf(10));
        put('ⅺ', BigDecimal.valueOf(11));
        put('ⅻ', BigDecimal.valueOf(12));
    }} );

    @SuppressWarnings("serial")
    private static final Map<Character, BigDecimal> CARDINAL = Collections.unmodifiableMap(new HashMap<Character, BigDecimal>() {{
        put('十', BigDecimal.valueOf(10));
        put('百', BigDecimal.valueOf(100));
        put('千', BigDecimal.valueOf(1000));
        put('万', BigDecimal.valueOf(10000));
        put('億', BigDecimal.valueOf(100000000));
        put('兆', BigDecimal.valueOf(1000000000000L));
        put('京', BigDecimal.valueOf(10000000000000000L));
        put('拾', BigDecimal.valueOf(10));
        put('陌', BigDecimal.valueOf(100));
        put('佰', BigDecimal.valueOf(100));
        put('阡', BigDecimal.valueOf(1000));
        put('仟', BigDecimal.valueOf(1000));
        put('萬', BigDecimal.valueOf(10000));
        put('割', BigDecimal.valueOf(0.1));
        put('分', BigDecimal.valueOf(0.01));
        put('厘', BigDecimal.valueOf(0.001));
        put('毛', BigDecimal.valueOf(0.0001));
    }} );

    @SuppressWarnings("serial")
    private static final Map<Character, String> DELIMITER = Collections.unmodifiableMap(new HashMap<Character, String>() {{
        put(',',  "Comma");
        //put('、', "Ideographic Comma");
    }} );

    @SuppressWarnings("serial")
    private static final Map<Character, String> DECIMAL = Collections.unmodifiableMap(new HashMap<Character, String>() {{
        put('.', "Full Stop");
        //put('．', "Fullwidth Full Stop");
    }} );

    static boolean isDelimiter(final Character c) {
        return Char.DELIMITER.containsKey(c);
    }

    static boolean isDecimalPoint(final Character c) {
        return Char.DECIMAL.containsKey(c);
    }

    static BigDecimal number(final Character c) {
        return Char.NUMBER.get(c);
    }

    static BigDecimal cardinal(final Character c) {
        return Char.CARDINAL.get(c);
    }

}
