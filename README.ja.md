# Suji

`Suji` は日本語や数字で表記された数値表現の文字列を実際の数値へ変換するライブラリです。
また、数値表現を漢数字に変換することもできます。
クラス `com.github.jikyo.suji.Converter` はそのような変換を行う2つのメソッド `values` と `kansujis` を提供します。

## `values`: 日本語数値表現から数値への変換

漢数字を数値変換できます。
文字列 `１つの価格が二兆30万五千十7円になります。` から値 `1` と `2000000005017` を抽出します。
また、 `打率は三割二分五厘です。` を値 `0.325` に抽出します。
返却される値は、 [Numeral](https://github.com/jikyo/suji4j/blob/master/src/main/java/com/github/jikyo/suji/Numeral.java) オブジェクトのリストです。
入力文字列に数値表現がない場合、 `Suji` は空リストを返却します。
`Numeral` オブジェクトは3つメソッド、 `value()` 、 `begin()` 、 `end()` をもちます。

* `value()`: 日本語表記が表す値の `BigDecimal` インスタンス
* `begin()`: 入力文字列で見つかった数値表現の開始位置
* `end()`: 入力文字列で見つかった数値表現の終了位置

## `kansujis`: 数値表現から日本語数値への変換

文字列 `20兆30万五千十7円になります。` は `二十兆三十万五千十七` へ変換されます。
bool 値であるフラグは `一` を表示するか否かを指定します。
`Converter.kansujis('1000万', true)` は `一千万` に変換されます。
`Converter.kansujis('1000万', false)` は `千万` に変換されます。
小数点以下の表記には *対応していない* ことに注意してください。
その為、 `32.01` は `三十二` に変換され、 `三十二割一厘` にはなりません。

返却される値は、 [Kansuji](https://github.com/jikyo/suji4j/blob/master/src/main/java/com/github/jikyo/suji/Kansuji.java) オブジェクトのリストです。
入力文字列に数値表現がない場合、空リストが返却されます。
`Kansuji` オブジェクトは3つメソッド、 `value()` 、 `begin()` 、 `end()` をもちます。

* `value()`: 漢数字文字列を表す `String` インスタンス
* `begin()`: 入力文字列で見つかった数値表現の開始位置
* `end()`: 入力文字列で見つかった数値表現の終了位置

`Suji` は one-pass パーサーです。
すなわち、入力テキストを先頭から末尾まで一度だけしか走査しません。

## Requirement

* `Suji` は依存関係のない Java ネイティブなライブラリです。
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
