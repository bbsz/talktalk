package com.talktalk.bobusky;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by sergej on 10.2.2017.
 */
public class DigitsGroupTest {

    @Test
    public void addDigit() {
        DigitsGroup dg = new DigitsGroup();
        assertTrue(dg.getDigits().isEmpty());
        assertNull(dg.getGroupType());

        dg.addLast(Digit.EIGHT);
    }

}