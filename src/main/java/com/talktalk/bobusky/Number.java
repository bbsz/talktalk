package com.talktalk.bobusky;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sergej on 8.2.2017.
 */
public class Number {

    private Integer value;
    private Digit digit;
    private List<DigitsGroup> digitsGroups;

    public Number(Integer value) {
        this.value = value;
        this.digitsGroups = new ArrayList<>();
        parseValue();
    }

    @VisibleForTesting
    Number(Integer value, List<DigitsGroup> digitsGroups) {
        this.value = value;
        this.digitsGroups = digitsGroups;
    }

    /**
     * A number constitutes a digit group if it cannot be captured by one instance of {@link Digit}
     */
    public boolean constitutesADigitGroup() {
        return Arrays.stream(Digit.values()).noneMatch(d -> d.value == value);
    }

    public int getValue() {
        return value;
    }

    public List<DigitsGroup> getDigitsGroups() {
        return digitsGroups;
    }

    private void parseValue() {


        if (value == Digit.ZERO.value) {
            addDigitGroup(Digit.ZERO);
            return;
        }

        int modulo = -1;
        do {
            modulo = addHighestDigit(value);
        } while (modulo > 1);
    }

    private int addHighestDigit(int numberValue) {
        Digit lastDigit = null;
        int previousMod = -1;
        for (Digit digit : Digit.getSortedExcludingZero()) {
            int mod = numberValue % digit.value;

            if (mod == numberValue && Digit.isSingleDigit(previousMod)) {
                break;
            }
            lastDigit = digit;
            previousMod = mod;
        }
        if (constitutesADigitGroup()) {
            addDigitGroup(lastDigit);
        } else {
            digit = lastDigit;
        }
        return previousMod;
    }

    private void addDigitGroup(Digit... digits) {
        DigitsGroup digitsGroup = new DigitsGroup();
        digitsGroup.add(digits);
        digitsGroups.add(digitsGroup);
    }

    @Override
    public String toString() {
        return "com.talktalk.bobusky.Number{" +
                "value=" + value +
                ", digitsGroups=" + digitsGroups +
                '}';
    }
}
