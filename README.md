# Suji

`Suji` is a converter library from Japanese number notation to numerical value, and from numerical notation to Japanese Kansuji notation.
The class, `com.github.jikyo.suji.Converter`, provides such methods, `values` and `kansujis`.

## `values` to convert from Japanese number notation to numerical value

The `String` `１つの価格が二兆30万五千十7円になります。` will be converted to two `BigDecimal`, `1` and `2000000005017`.
And also, `打率は三割二部五厘です。`  will be a `0.325`.
The return value is a list of suji [Numeral](https://github.com/jikyo/suji4j/blob/master/src/main/java/com/github/jikyo/suji/Numeral.java) objects.
If the input string has no number notation, `Suji` returns a empty list.
The Numeral object has three methods: `value()`, `begin()`, and `end()`:

* `value()`: a `BigDecimal` instance of a numerical value for the number notation.
* `begin()`: the begin index (`int`) of the found number notation at the input string.
* `end()`: the end index (`int`) of the found number notation.

## `kansujis` to convert from numeric notation to Japanese Kansuji notation

The `String` `20兆30万五千十7円になります。` will be converted to the Kansuji string, `二十兆三十万五千十七`.
The boolean flag one is interpreted as whether to display the first character `一` or not.
The output of `Converter.kansujis('1000万', true)` will be converted to `一千万`, and the output of `Converter.kansujis('1000万', false)` will be converted to `千万`.
Note that `kansujis` does not support numerical notation after the decimal point.
If the input string is `32.01`, the output will `三十二`, not `三十二割一厘`.

The return value is a list of suji [Kansuji](https://github.com/jikyo/suji4j/blob/master/src/main/java/com/github/jikyo/suji/Kansuji.java) objects.
If the input string has no number notation, `Suji` returns a empty list.
The `Kansuji` object has three methods: `value()`, `begin()`, and `end()`:

* `value()`: a `String` instance of a Kansuji notation.
* `begin()`: the begin index (`int`) of the found number notation at the input string.
* `end()`: the end index (`int`) of the found number notation.

`Suji` is a one-pass parser.
That is, `Suji` parse a source text from the head to the end only once.

[日本語](README.ja.md) (For Japanese Documentation)

## Requirement

* `Suji` is pure Java code with no dependencies.
* Java 8+

## Usage

```java
import com.github.jikyo.suji.Converter;
import com.github.jikyo.suji.Kansuji;
import com.github.jikyo.suji.Numeral;

public class Main {
    public static void main(String args[]) {
        String src = "１つの価格が二兆30万五千十7円になります。";
        List<Numeral> results = Converter.values(src);
        System.out.println(results); // [{val: 1, begin: 0, end: 1}, {val: 2000000305017, begin: 6, end: 15}]
        Numeral n = results.get(1);
        System.out.println(n.value()); // 2000000305017
        System.out.println(src.substring(n.begin(), n.end())); // 二兆30万五千十7

        src = "価格は￥10,000,000です。";
        List<Kansuji> kansujis;
        kansujis = Converter.kansujis(src, true);
        System.out.println(kansujis); // [{val: 一千万, begin: 4, end: 14}]
        System.out.println(kansujis.get(0).value()); // 一千万
        kansujis = Converter.kansujis(src, false);
        System.out.println(kansujis); // [{val: 千万, begin: 4, end: 14}]
        System.out.println(kansujis.get(0).value()); // 千万
    }
}
```

## Test

```bash
# unit test
$ mvn test
# coverage
$ mvn test jacoco:report
```

## pom.xml

```xml
        <dependency>
            <groupId>com.github.jikyo</groupId>
            <artifactId>suji</artifactId>
            <version>0.0.4</version>
        </dependency>
```
