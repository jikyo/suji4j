package com.github.jikyo.suji;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Converter {

    private Converter () {
        throw new AssertionError();
    }

    public static List<Numeral> values(final String src) {
        List<Numeral> val = new ArrayList<>();
        Accumulator acc = new Accumulator();

        for (int i = 0; i < src.length(); i++) {
            Character c = src.charAt(i);

            if (Char.isDelimiter(c)) {
                acc.increment(i);
                continue;
            }

            if (Char.isDecimalPoint(c)) {
                acc.turnToDecimalState(i);
                continue;
            }

            BigDecimal cardinal = Char.cardinal(c);
            if (cardinal != null) {
                if (acc.inside()) {
                    acc.attachCardinal(i, cardinal);
                } else if (BigDecimal.ONE.compareTo(cardinal) < 0) {
                    acc.attachCardinal(i, cardinal);
                }
                continue;
            }

            BigDecimal number = Char.number(c);
            if (number != null) {
                acc.attachNumber(i, number);
                continue;
            }

            if (acc.inside()) {
                val.add(acc.value());
                acc = new Accumulator();
            }
        }

        if (acc.inside()) {
            val.add(acc.value());
        }

        return val;
    }

    public static List<Kansuji> kansujis(final String src, final boolean one) {
        List<Kansuji> val = new ArrayList<>();
        Converter.values(src).forEach(n ->
                val.add(new Kansuji(Kansujinizer.kansuji(n.value(), one), n.begin(), n.end()))
        );
        return val;
    }
}
