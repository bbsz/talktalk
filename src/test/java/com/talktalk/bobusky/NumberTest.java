package com.talktalk.bobusky;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by sergej on 8.2.2017.
 */
public class NumberTest {

    @Test
    public void constitutesADigitsGroup() {
        Arrays.stream(Digit.values()).forEach(d -> {
            Number number = new Number(d.value);
            assertFalse(number.constitutesADigitGroup());
        });

        assertTrue(new Number(21).constitutesADigitGroup());
        assertTrue(new Number(87).constitutesADigitGroup());
        assertTrue(new Number(53).constitutesADigitGroup());
        assertTrue(new Number(121).constitutesADigitGroup());
        assertTrue(new Number(801).constitutesADigitGroup());
        assertTrue(new Number(854).constitutesADigitGroup());
        assertTrue(new Number(888).constitutesADigitGroup());
        assertTrue(new Number(1001).constitutesADigitGroup());
        assertTrue(new Number(1010).constitutesADigitGroup());
        assertTrue(new Number(1111).constitutesADigitGroup());
        assertTrue(new Number(9900).constitutesADigitGroup());
        assertTrue(new Number(9990).constitutesADigitGroup());
        assertTrue(new Number(9999).constitutesADigitGroup());
        assertTrue(new Number(9999009).constitutesADigitGroup());
    }

    @Test
    public void convert_singleDigit() {
        Digit.getSingleDigits().forEach(d -> convertDigits(d));
    }

    @Test
    public void convert_Teens() {
        Digit.getTeens().forEach(d -> convertDigits(d));
    }

    @Test
    public void convert_Tens() {
        for (Digit tensDigit : Digit.getTens()) {
            for (Digit singleDigit : Digit.getSingleDigits()) {
                if (singleDigit == Digit.ZERO) {
                    convertDigits(tensDigit);
                    continue;
                }
                convertDigits(tensDigit, singleDigit);
            }
        }
    }

    //    @Test
    public void convert_27() {
        DigitsGroup expectedDigitsGroup = new DigitsGroup();
        expectedDigitsGroup.addLast(Digit.TWENTY);
        expectedDigitsGroup.addLast(Digit.SEVEN);

        Number expectedNumber = new Number(27, Lists.newArrayList(expectedDigitsGroup));
        Number number = new Number(27);

        assertNumber(expectedNumber, number);
    }

    private void convertDigits(Digit... digits) {
        DigitsGroup expectedDigitsGroup = new DigitsGroup();
        expectedDigitsGroup.add(digits);

        Integer value = addUp(digits);
        Number expectedNumber = new Number(value, Lists.newArrayList(expectedDigitsGroup));
        Number number = new Number(value);

        assertNumber(expectedNumber, number);
    }

    private Integer addUp(Digit... digits) {
        Integer value = 0;
        for (Digit digit : digits) {
            value += digit.value;
        }
        return value;
    }

    private void assertNumber(Number expectedNumber, Number actualNumber) {
        assertThat("ExpectNumber " + expectedNumber.getValue(), expectedNumber.getDigitsGroups().size(), is(actualNumber.getDigitsGroups().size()));
        for (int i = 0; i < expectedNumber.getDigitsGroups().size(); i++) {
            DigitsGroup actualDigitsGroup = actualNumber.getDigitsGroups().get(i);
            DigitsGroup expectedDigitsGroup = expectedNumber.getDigitsGroups().get(i);
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

}