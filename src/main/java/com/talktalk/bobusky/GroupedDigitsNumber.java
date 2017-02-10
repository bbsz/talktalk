package com.talktalk.bobusky;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergej on 10.2.2017.
 */
public class GroupedDigitsNumber extends Number {

    private List<GroupedDigits> groupedDigitses;

    public GroupedDigitsNumber(Integer value) {
        super(value);
        this.groupedDigitses = new ArrayList<>();
        checkDigitsGroupNumber();
        parseValue();
    }

    @VisibleForTesting
    GroupedDigitsNumber(Integer value, List<GroupedDigits> groupedDigitses) {
        super(value);
        this.groupedDigitses = groupedDigitses;
    }

    public List<GroupedDigits> getGroupedDigitses() {
        return groupedDigitses;
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
        GroupedDigits groupedDigits = new GroupedDigits();
        groupedDigits.add(digits);
        groupedDigitses.add(groupedDigits);
    }

    private void checkDigitsGroupNumber() {
        if (isSingleDigitNumber()) {
            throw new RuntimeException("Not a digits group number...");
        }
    }
}
