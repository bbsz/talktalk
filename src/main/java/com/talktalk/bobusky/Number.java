package com.talktalk.bobusky;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sergej on 8.2.2017.
 */
public abstract class Number {

    protected Integer value;

    public Number(Integer value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    protected abstract void parseValue();

    /**
     * A number constitutes a  single digit number if it can be fully captured by one instance of {@link Digit}
     */
    protected boolean isSingleDigitNumber() {
        return Arrays.stream(Digit.values()).anyMatch(d -> d.value == value);
    }
}
