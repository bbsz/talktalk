package com.talktalk.bobusky;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergej on 10.2.2017.
 */
public class DigitsGroupNumber extends Number {

    private List<DigitsGroup> digitsGroups;

    public DigitsGroupNumber(Integer value) {
        super(value);
        this.digitsGroups = new ArrayList<>();
        checkDigitsGroupNumber();
        parseValue();
    }

    @VisibleForTesting
    DigitsGroupNumber(Integer value, List<DigitsGroup> digitsGroups) {
        super(value);
        this.digitsGroups = digitsGroups;
    }

    public List<DigitsGroup> getDigitsGroups() {
        return digitsGroups;
    }

    protected void parseValue() {
        int modulo = -1;
        do {
            modulo = addHighestDigit(value);
        } while (modulo > 0);
    }

    private int addHighestDigit(int numberValue) {
        Digit lastDigit = null;
        int previousMod = -1;
        for (Digit digit : Digit.getSortedExcludingZero()) {
            int mod = numberValue % digit.value;

            if (mod == numberValue && previousMod > 0) {
                break;
            }
            lastDigit = digit;
            previousMod = mod;
        }
        addDigitGroup(lastDigit);
        return previousMod;
    }

    private void addDigitGroup(Digit... digits) {
        DigitsGroup digitsGroup = new DigitsGroup();
        digitsGroup.add(digits);
        digitsGroups.add(digitsGroup);
    }

    private void checkDigitsGroupNumber() {
        if (isSingleDigitNumber()) {
            throw new RuntimeException("Not a digits group number...");
        }
    }
}
