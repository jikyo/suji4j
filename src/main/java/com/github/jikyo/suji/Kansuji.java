package com.github.jikyo.suji;

public class Kansuji {

    private String val;
    private int beg;
    private int end;

    public Kansuji(String v, int b, int e) {
        this.val = v;
        this.beg = b;
        this.end = e;
    }

    public static Kansuji valueOf(String v, int b, int e) {
        return new Kansuji(v, b, e);
    }

    public String value() {
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
        Kansuji k = (Kansuji) obj;
        if (!k.value().equals(this.value())) {
            return false;
        }
        if (k.begin() != this.begin()) {
            return false;
        }
        if (k.end() != this.end()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{val: " + this.val
                + ", begin: " + String.valueOf(this.beg)
                + ", end: " + String.valueOf(this.end)
                + "}";
    }

}
