# Suji

`Suji` is a converter library from Japanese number notation to numerical value.
Japanese number notation can include Kansuji.
The `String` `１つの価格が二兆30万五千十7円になります。` will be converted to two `BigDecimal`, `1` and `2000000005017`.
And also, `打率は三割二部五厘です。`  will be a `0.325`.
The return value is a list of suji `Numeral` objects.
If the input string has no number notation, `Suji` returns a empty list.
The Numeral object has three methods: `value()`, `begin()`, and `end()`:

* `value()`: a `BigDecimal` instance of a numerical value for the number notation.
* `begin()`: the begin index (`int`) of the found number notation at the input string.
* `end()`: the end index (`int`) of the found number notation.

`Suji` is a one-pass parser.
That is, `Suji` parse a source text from the head to the end only once.

[日本語](README.ja.md) (For Japanese Documentation)


# Requirement

* `Suji` is pure Java code with no dependencies.
* Java 8+


# Installation

(in preparation)

```bash
$ git clone https://github.com/jikyo/suji4j.git
$ cd suji4j
$ mvn install
```


# Usage

```java
import com.github.jikyo.suji.Converter;
import com.github.jikyo.suji.Numeral;


public class Main {
    public static void main(String args[]) {
        String src = "１つの価格が二兆30万五千十7円になります。";
        List<Numeral> results = Converter.values(src);
        System.out.println(results); // [{val: 1, begin: 0, end: 1}, {val: 2000000305017, begin: 6, end: 15}]
        Numeral n = results.get(1);
        System.out.println(n.value()); // 2000000305017
        System.out.println(src.substring(n.begin(), n.end())); // 二兆30万五千十7
    }
}
```


# pom.xml

```xml
        <dependency>
            <groupId>com.github.jikyo.suji</groupId>
            <artifactId>suji</artifactId>
            <version>0.0.3</version>
        </dependency>
```