package com.github.jikyo.suji;

import java.math.BigDecimal;

class Accumulator {

    private static BigDecimal OKU = BigDecimal.valueOf(100000000L);

    private boolean inside = false;
    private int beg = -1;
    private int end = -1;
    private BigDecimal val = BigDecimal.ZERO;
    private BigDecimal valCardinal = BigDecimal.ZERO;
    private BigDecimal lastCardinal = BigDecimal.valueOf(10);
    private BigDecimal base = BigDecimal.valueOf(10);
    private BigDecimal oku = BigDecimal.ZERO;

    boolean inside() {
        return this.inside;
    }

    void increment(final int index) {
        if (!this.inside) {
            return;
        }
        if (this.beg < 0) {
            this.beg = index;
            this.end = index + 1;
            return;
        }
        ++this.end;
    }

    void turnToDecimalState(final int index) {
        if (!this.inside) {
            this.inside = true;
        }
        this.increment(index);
        this.base = BigDecimal.valueOf(0.1);
    }

    void attachCardinal(final int index, final BigDecimal cardinal) {
        this.inside = true;
        this.increment(index);

        BigDecimal val = (0 == this.val.compareTo(BigDecimal.ZERO))
            ? BigDecimal.ONE
            : this.val;

        if (this.lastCardinal.compareTo(cardinal) < 0) {
            this.valCardinal = (this.val.compareTo(BigDecimal.ZERO) == 0 && this.valCardinal.compareTo(BigDecimal.ZERO) != 0)
                ? this.valCardinal.multiply(cardinal)
                : cardinal.multiply(this.valCardinal.add(val));
        } else {
            this.valCardinal = this.valCardinal.add(cardinal.multiply(val));
        }

        if (Accumulator.OKU.compareTo(this.valCardinal) <= 0) {
            this.oku = this.oku.add(this.valCardinal);
            this.valCardinal = BigDecimal.ZERO;
        }
 
        this.lastCardinal = cardinal;
        this.val = BigDecimal.ZERO;
    }

    void attachNumber(final int index, final BigDecimal number) {
        this.inside = true;
        this.increment(index);
        if (this.base.compareTo(BigDecimal.ONE) < 0) {
            this.val = this.val.add(this.base.multiply(number));
            this.base = this.base.multiply(BigDecimal.valueOf(0.1));
        } else {
            this.val = number.add(this.base.multiply(this.val));
        }
    }

    Numeral value() {
        return Numeral.valueOf(this.oku.add(this.val.add(this.valCardinal)), this.beg, this.end);
    }

    @Override
    public String toString() {
        return "acc:\n"
                + "\tinside: " + String.valueOf(this.inside) + "\n"
                + "\tbeg: " + String.valueOf(this.beg) + "\n"
                + "\tend: " + String.valueOf(this.end) + "\n"
                + "\tval: " + String.valueOf(this.val) + "\n"
                + "\tvalCardinal: " + String.valueOf(this.valCardinal) + "\n"
                + "\tlastCardinal: " + String.valueOf(this.lastCardinal) + "\n"
                + "\tbase: " + String.valueOf(this.base) + "\n"
                + "\toku: " + String.valueOf(this.oku) + "\n";
    }

}