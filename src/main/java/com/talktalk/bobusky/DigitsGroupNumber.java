package com.talktalk.bobusky;

import com.google.common.annotations.VisibleForTesting;

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

        List<Digit> group = new ArrayList<>();
        int tempValue = value;

        while (tempValue > 0) {
            Digit lastDigit = null;
            int previousMod = -1;

            for (Digit digit : Digit.getSortedExcludingZero()) {
                int mod = tempValue % digit.value;

                if (mod == tempValue) {
                    break;
                }
                lastDigit = digit;
                previousMod = mod;
            }
            group.add(lastDigit);

            if (previousMod > 0) {
                tempValue = previousMod;
            } else {
                break;
            }
        }
        addDigitGroup(group);
    }

    private void addDigitGroup(List<Digit> digits) {
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
