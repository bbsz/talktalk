package com.talktalk.bobusky;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sergej on 8.2.2017.
 */
public enum Digit {

    ZERO(0, "zero", DigitType.SINGLE_DIGIT),
    ONE(1, "one", DigitType.SINGLE_DIGIT),
    TWO(2, "two", DigitType.SINGLE_DIGIT),
    THREE(3, "three", DigitType.SINGLE_DIGIT),
    FOUR(4, "four", DigitType.SINGLE_DIGIT),
    FIVE(5, "five", DigitType.SINGLE_DIGIT),
    SIX(6, "six", DigitType.SINGLE_DIGIT),
    SEVEN(7, "seven", DigitType.SINGLE_DIGIT),
    EIGHT(8, "eight", DigitType.SINGLE_DIGIT),
    NINE(9, "nine", DigitType.SINGLE_DIGIT),
    TEN(10, "ten", DigitType.TEENS),
    ELEVEN(11, "eleven", DigitType.TEENS),
    TWELVE(12, "twelve", DigitType.TEENS),
    THIRTEEN(13, "thirteen", DigitType.TEENS),
    FOURTEEN(14, "fourteen", DigitType.TEENS),
    FIFTEEN(15, "fifteen", DigitType.TEENS),
    SIXTEEN(16, "sixteen", DigitType.TEENS),
    SEVENTEEN(17, "seventeen", DigitType.TEENS),
    EIGHTEEN(18, "eighteen", DigitType.TEENS),
    NINETEEN(19, "nineteen", DigitType.TEENS),
    TWENTY(20, "twenty", DigitType.TENS),
    THIRTY(30, "thirty", DigitType.TENS),
    FORTY(40, "forty", DigitType.TENS),
    FIFTY(50, "fifty", DigitType.TENS),
    SIXTY(60, "sixty", DigitType.TENS),
    SEVENTY(70, "seventy", DigitType.TENS),
    EIGHTY(80, "eighty", DigitType.TENS),
    NINETY(90, "ninety", DigitType.TENS),
    HUNDRED(100, "hundred", DigitType.HOUNDREDS),
    THOUSAND(1000, "thousand", DigitType.THOUSANDS),
    MILION(1000000, "milion", DigitType.MILLIONS);

    public final Integer value;
    public final String text;
    public final DigitType type;

    Digit(Integer value, String text, DigitType type) {
        this.value = value;
        this.text = text;
        this.type = type;
    }

    public static List<Digit> getSortedExcludingZero() {
        Stream<Digit> digits = Arrays.stream(Digit.values());

        return digits.filter(d -> d.value != ZERO.value)
                .sorted((o1, o2) -> o1.value.compareTo(o2.value))
                .collect(Collectors.<Digit>toList());
    }
}
