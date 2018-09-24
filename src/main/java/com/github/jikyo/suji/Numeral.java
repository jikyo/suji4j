package com.github.jikyo.suji;

import java.math.BigDecimal;

public class Numeral {

    private BigDecimal val;
    private int beg;
    private int end;

    public Numeral(BigDecimal v, int b, int e) {
        this.val = v;
        this.beg = b;
        this.end = e;
    }

    public static Numeral valueOf(BigDecimal v, int b, int e) {
        return new Numeral(v, b, e);
    }

    public BigDecimal value() {
        return this.val;
    }

    public int begin() {
        return this.beg;
    }

    public int end() {
        return this.end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Numeral n = (Numeral) obj;
        if (0 != n.value().compareTo(this.value())) {
            return false;
        }
        if (n.begin() != this.begin()) {
            return false;
        }
        if (n.end() != this.end()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{val: " + this.val.toString()
                + ", begin: " + String.valueOf(this.beg)
                + ", end: " + String.valueOf(this.end)
                + "}";
    }

}
