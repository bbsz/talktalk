package com.talktalk.bobusky;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by sergej on 10.2.2017.
 */
public class GroupTypeTest {

    @Test
    public void mapDigitTypeToGroupType() {
        assertThat(GroupType.mapToGroupType(DigitType.TENS), is(GroupType.TENS));
        assertThat(GroupType.mapToGroupType(DigitType.HOUNDREDS), is(GroupType.HOUNDREDS));
        assertThat(GroupType.mapToGroupType(DigitType.THOUSANDS), is(GroupType.THOUSANDS));
        assertThat(GroupType.mapToGroupType(DigitType.MILLIONS), is(GroupType.MILLIONS));
    }

    @Test(expected = RuntimeException.class)
    public void mapDigitTypeToGroupType_SINGLEDIGIT() {
        GroupType.mapToGroupType(DigitType.SINGLE_DIGIT);
    }

    @Test(expected = RuntimeException.class)
    public void mapDigitTypeToGroupType_TEENS() {
        GroupType.mapToGroupType(DigitType.TEENS);
    }

    @Test(expected = RuntimeException.class)
    public void mapDigitTypeToGroupType_NULL() {
        GroupType.mapToGroupType(null);
    }

    @Test
    public void canMapToGroup() {
        assertTrue(GroupType.canMapToGroup(DigitType.HOUNDREDS));
        assertTrue(GroupType.canMapToGroup(DigitType.MILLIONS));
        assertFalse(GroupType.canMapToGroup(DigitType.SINGLE_DIGIT));
        assertFalse(GroupType.canMapToGroup(DigitType.TEENS));
    }
}