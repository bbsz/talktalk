package com.talktalk.bobusky;

import com.google.common.annotations.VisibleForTesting;

/**
 * Created by sergej on 10.2.2017.
 */
public class SingleDigitNumber extends Number {

    private Digit digit;

    public SingleDigitNumber(Integer value) {
        super(value);
        checkSingleDigitNumber();
    }

    @VisibleForTesting
    SingleDigitNumber(Integer value, Digit digit) {
        super(value);
        this.digit = digit;
    }

    public Digit getDigit() {
        return digit;
    }

    protected void parseValue() {
        if (value == Digit.ZERO.value) {
            digit = Digit.ZERO;
            return;
        }

        Digit lastDigit = null;
        int previousMod = -1;

        for (Digit digit : Digit.getSortedExcludingZero()) {
            int mod = value % digit.value;

            if (mod == value && previousMod == 0) {
                break;
            }
            lastDigit = digit;
            previousMod = mod;
        }
        digit = lastDigit;
    }

    private void checkSingleDigitNumber() {
        if (!isSingleDigitNumber()) {
            throw new RuntimeException("Not a single digit number...");
        }
    }
}
