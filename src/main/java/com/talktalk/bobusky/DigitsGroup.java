package com.talktalk.bobusky;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergej on 8.2.2017.
 */
public class DigitsGroup {

    private GroupType groupType;
    private List<Digit> digits;

    public DigitsGroup() {
        this.digits = new ArrayList<>();
    }

    public void addLast(Digit digit) {
        digits.add(digit);
        resolveType();
    }

    public void add(List<Digit> digits) {
        for (Digit digit : digits) {
            addLast(digit);
        }
    }

    public List<Digit> getDigits() {
        return digits;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    /**
     * Resolves group type based on the type of the first digit in the group...
     */
    private void resolveType() {
        if (groupType == null && !digits.isEmpty()) {
            Digit firstDigit = digits.get(0);

            if (GroupType.canMapToGroup(firstDigit.type)) {
                groupType = GroupType.mapToGroupType(firstDigit.type);
            } else {
                throw new RuntimeException("Leading digit cannot be mapped...");
            }
        }
    }
}
