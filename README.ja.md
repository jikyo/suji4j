# Suji

`Suji` は日本語や数字で表記された数値表現の文字列を実際の数値へ変換するライブラリです。
漢数字を数値変換できます。
文字列 `１つの価格が二兆30万五千十7円になります。` から値 `1` と `2000000005017` を抽出します。
また、 `打率は三割二分五厘です。` を値 `0.325` に抽出します。
返却される値は、 `Numeral` オブジェクトのリストです。
入力文字列に数値表現がない場合、 `Suji` は空リストを返却します。
`Numeral` オブジェクトは3つメソッド、 `value()` 、 `begin()` 、 `end()` をもちます。

* `value()`: 日本語表記が表す値の `BigDecimal` インスタンス
* `begin()`: 入力文字列で見つかった数値表現の開始位置
* `end()`: 入力文字列で見つかった数値表現の終了位置

`Suji` は one-pass パーサーです。
すなわち、入力テキストを先頭から末尾まで一度だけしか走査しません。


# Requirement

* `Suji` は依存関係のない Java ネイティブなライブラリです。
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
            <groupId>com.github.jikyo</groupId>
            <artifactId>suji</artifactId>
            <version>0.0.3</version>
        </dependency>
```