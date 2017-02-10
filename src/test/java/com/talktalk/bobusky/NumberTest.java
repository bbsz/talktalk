package com.talktalk.bobusky;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

/**
 * Created by sergej on 8.2.2017.
 */
public class NumberTest {

    @Test
    public void isASingleDigitNumber() {
        Arrays.stream(Digit.values()).forEach(d -> {
            //  If this was not a single digit number, exception would be thrown...
            Number number = new OneDigitNumber(d.value);
        });
    }

    @Test(expected = RuntimeException.class)
    public void isNotASingleDigitNumber() {
        //If is not a single digit number, exception is thrown...
        new OneDigitNumber(21);
    }

    @Test
    public void isADigitsGroupNumber() {
        new GroupedDigitsNumber(21);
        new GroupedDigitsNumber(22);
        new GroupedDigitsNumber(1111);
        new GroupedDigitsNumber(333);
        new GroupedDigitsNumber(11111111);
    }

    @Test(expected = RuntimeException.class)
    public void isNotADigitsGroupNumber() {
        //If is not a single digit number, exception is thrown...
        new GroupedDigitsNumber(20);
    }

    @Test
    public void convertDigits(){
        Arrays.stream(Digit.values()).forEach(d -> convertSingleDigitNumber(d));
    }

    @Test
    public void convert_singleDigit() {
        getSingleDigits().forEach(d -> convertSingleDigitNumber(d));
    }

    @Test
    public void convert_Teens() {
        getTeens().forEach(d -> convertSingleDigitNumber(d));
    }

    @Test
    public void convert_Tens() {
        for (Digit tensDigit : getTens()) {
            for (Digit singleDigit : getSingleDigits()) {
                if (singleDigit == Digit.ZERO) {
                    convertSingleDigitNumber(tensDigit);
                    continue;
                }
                convertDigitsGroupNumber(tensDigit, singleDigit);
            }
        }
    }

    @Test
    public void convert_Houndreds() {
        for (Digit houndredsDigit : getHoundreds()) {
            for (Digit tensDigit : getTens()) {
                for (Digit singleDigit : getSingleDigits()) {
                    if (singleDigit == Digit.ZERO) {
                        convertSingleDigitNumber(tensDigit);
                        continue;
                    }
                    convertDigitsGroupNumber(houndredsDigit, tensDigit, singleDigit);
                }
            }
        }
    }

    private void convertSingleDigitNumber(Digit digit) {
        OneDigitNumber expected = new OneDigitNumber(digit.value, digit);
        OneDigitNumber actual = new OneDigitNumber(digit.value);
        assertNumber(expected, actual);
    }

    private void convertDigitsGroupNumber(Digit... digits) {
        GroupedDigits expectedGroupedDigits = new GroupedDigits();
        expectedGroupedDigits.add(Arrays.asList(digits));
        Integer value = addUp(digits);

        GroupedDigitsNumber expected = new GroupedDigitsNumber(value, Lists.newArrayList(expectedGroupedDigits));
        GroupedDigitsNumber actual = new GroupedDigitsNumber(value);
        assertNumber(expected, actual);
    }

    private Integer addUp(Digit... digits) {
        Integer value = 0;
        for (Digit digit : digits) {
            value += digit.value;
        }
        return value;
    }

    private void assertNumber(OneDigitNumber expected, OneDigitNumber actual) {
        assertThat(actual.getDigit(), is(expected.getDigit()));
    }

    private void assertNumber(GroupedDigitsNumber expected, GroupedDigitsNumber actual) {
        assertThat("ExpectNumber " + expected.getValue(), actual.getGroupedDigitses().size(), is(expected.getGroupedDigitses().size()));
        for (int i = 0; i < expected.getGroupedDigitses().size(); i++) {
            GroupedDigits actualGroupedDigits = actual.getGroupedDigitses().get(i);
            GroupedDigits expectedGroupedDigits = expected.getGroupedDigitses().get(i);
            assertDigitGroup(expectedGroupedDigits, actualGroupedDigits);
        }
    }

    private void assertDigitGroup(GroupedDigits expectedDG, GroupedDigits actualDG) {
        assertThat(actualDG.getDigits().size(), is(expectedDG.getDigits().size()));

        for (int i = 0; i < expectedDG.getDigits().size(); i++) {
            Digit expectedDigit = expectedDG.getDigits().get(i);
            Digit actualDigit = actualDG.getDigits().get(i);
            assertSame(expectedDigit, actualDigit);
        }
    }

    private List<Digit> getSingleDigits() {
        Stream<Digit> digits = Arrays.stream(Digit.values());
        return digits.filter(d -> d.type == DigitType.SINGLE_DIGIT).collect(Collectors.toList());
    }

    private List<Digit> getTeens() {
        Stream<Digit> digits = Arrays.stream(Digit.values());
        return digits.filter(d -> d.type == DigitType.TEENS).collect(Collectors.toList());
    }

    private List<Digit> getTens() {
        Stream<Digit> digits = Arrays.stream(Digit.values());
        return digits.filter(d -> d.type == DigitType.TENS).collect(Collectors.toList());
    }

    private List<Digit> getHoundreds() {
        Stream<Digit> digits = Arrays.stream(Digit.values());
        return digits.filter(d -> d.type == DigitType.HOUNDREDS).collect(Collectors.toList());
    }

}