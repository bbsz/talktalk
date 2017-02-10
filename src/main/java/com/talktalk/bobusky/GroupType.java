package com.talktalk.bobusky;

/**
 * Created by sergej on 9.2.2017.
 */
public enum GroupType {
    TENS, HOUNDREDS, THOUSANDS, MILLIONS;

    public static GroupType mapToGroupType(DigitType digitType) {
        if (digitType == DigitType.SINGLE_DIGIT || digitType == DigitType.TEENS) {
            throw new RuntimeException(String.format("Digits of type {%s, %s} cannot be mapped to a group.", DigitType.SINGLE_DIGIT, DigitType.TEENS));
        }
        if (digitType == DigitType.TENS) {
            return TENS;
        } else if (digitType == DigitType.HOUNDREDS) {
            return HOUNDREDS;
        } else if (digitType == DigitType.THOUSANDS) {
            return THOUSANDS;
        } else if (digitType == DigitType.MILLIONS) {
            return MILLIONS;
        }

        throw new RuntimeException(String.format("Unrecognized digit type..."));
    }

    public static boolean canMapToGroup(DigitType digitType) {
        return digitType != DigitType.SINGLE_DIGIT && digitType != DigitType.TEENS;
    }
}
