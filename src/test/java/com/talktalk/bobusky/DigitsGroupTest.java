package com.talktalk.bobusky;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by sergej on 10.2.2017.
 */
public class DigitsGroupTest {

    @Test
    public void resolveDigitsGroup_Tens() {
        DigitsGroup dg = new DigitsGroup();
        assertTrue(dg.getDigits().isEmpty());
        assertNull(dg.getGroupType());

        dg.addLast(Digit.EIGHTY);
        assertThat(dg.getGroupType(), is(GroupType.TENS));
        dg.addLast(Digit.EIGHT);
        assertThat(dg.getGroupType(), is(GroupType.TENS));
    }

//    @Test
    public void resolveDigitsGroup_Houndreds() {
        DigitsGroup dg = new DigitsGroup();
        assertTrue(dg.getDigits().isEmpty());
        assertNull(dg.getGroupType());

//        dg.addLast(Digit.);
        assertThat(dg.getGroupType(), is(GroupType.TENS));
        dg.addLast(Digit.EIGHT);
        assertThat(dg.getGroupType(), is(GroupType.TENS));
    }


}