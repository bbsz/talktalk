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
            Number number = new SingleDigitNumber(d.value);
        });
    }

    @Test(expected = RuntimeException.class)
    public void isNotASingleDigitNumber() {
        //If is not a single digit number, exception is thrown...
        new SingleDigitNumber(21);
    }

    @Test
    public void isADigitsGroupNumber() {
        new DigitsGroupNumber(21);
        new DigitsGroupNumber(22);
        new DigitsGroupNumber(1111);
        new DigitsGroupNumber(333);
        new DigitsGroupNumber(11111111);
    }

    @Test(expected = RuntimeException.class)
    public void isNotADigitsGroupNumber() {
        //If is not a single digit number, exception is thrown...
        new DigitsGroupNumber(20);
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



    private void convertSingleDigitNumber(Digit digit) {
        SingleDigitNumber expected = new SingleDigitNumber(digit.value, digit);
        SingleDigitNumber actual = new SingleDigitNumber(digit.value);
        assertNumber(expected, actual);
    }

    private void convertDigitsGroupNumber(Digit... digits) {
        DigitsGroup expectedDigitsGroup = new DigitsGroup();
        expectedDigitsGroup.add(Arrays.asList(digits));
        Integer value = addUp(digits);

        DigitsGroupNumber expected = new DigitsGroupNumber(value, Lists.newArrayList(expectedDigitsGroup));
        DigitsGroupNumber actual = new DigitsGroupNumber(value);
        assertNumber(expected, actual);
    }

    private Integer addUp(Digit... digits) {
        Integer value = 0;
        for (Digit digit : digits) {
            value += digit.value;
        }
        return value;
    }

    private void assertNumber(SingleDigitNumber expected, SingleDigitNumber actual) {
        assertThat(actual.getDigit(), is(expected.getDigit()));
    }

    private void assertNumber(DigitsGroupNumber expected, DigitsGroupNumber actual) {
        assertThat("ExpectNumber " + expected.getValue(), actual.getDigitsGroups().size(), is(expected.getDigitsGroups().size()));
        for (int i = 0; i < expected.getDigitsGroups().size(); i++) {
            DigitsGroup actualDigitsGroup = actual.getDigitsGroups().get(i);
            DigitsGroup expectedDigitsGroup = expected.getDigitsGroups().get(i);
            assertDigitGroup(expectedDigitsGroup, actualDigitsGroup);
        }
    }

    private void assertDigitGroup(DigitsGroup expectedDG, DigitsGroup actualDG) {
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

}