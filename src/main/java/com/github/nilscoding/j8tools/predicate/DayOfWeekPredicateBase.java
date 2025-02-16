package com.github.nilscoding.j8tools.predicate;

import java.time.DayOfWeek;

/**
 * Implementation helper class for day-of-week predicates.
 * @author NilsCoding
 */
public abstract class DayOfWeekPredicateBase {

    /**
     * Bit mask: none.
     */
    protected static final byte BITMASK_NONE = 0b00000000;
    /**
     * Bit mask: weekdays.
     */
    protected static final byte BITMASK_WEEKDAYS = 0b00111110;
    /**
     * Bit mask: weekends.
     */
    protected static final byte BITMASK_WEEKEND = (byte) 0b11000000;
    /**
     * Bit mask: all days.
     */
    protected static final byte BITMASK_ALL = (byte) 0b11111110;
    /**
     * Bit mask to convert a byte to an int.
     */
    protected static final int BYTE_TO_INT_MASK = 0xFF;

    /**
     * Days of week.
     */
    protected byte daysOfWeek;

    /**
     * Stores the values of the given days of week.
     * @param daysOfWeek days of week to store
     */
    protected void storeDaysOfWeek(final DayOfWeek... daysOfWeek) {
        if ((daysOfWeek == null) || (daysOfWeek.length == 0)) {
            return;
        }
        for (int i = 0; i < daysOfWeek.length; i++) {
            DayOfWeek dow = daysOfWeek[i];
            if (dow == null) {
                continue;
            }
            int dowValue = dow.getValue();
            this.daysOfWeek = (byte) (this.daysOfWeek | (1 << dowValue));
        }
    }

    /**
     * Stores the value of the given days of week via bit mask.
     * @param daysOfWeek days of week via bit mask
     */
    protected void storeDaysOfWeek(final byte daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    /**
     * Checks if the given day-of-week is contained in these values.
     * @param dow day-of-week to check
     * @return true if value is contained, false otherwise
     */
    protected boolean containsDayOfWeek(DayOfWeek dow) {
        if (dow == null) {
            return false;
        }
        if (this.daysOfWeek == BITMASK_NONE) {
            return false;
        }
        if (this.daysOfWeek == BITMASK_ALL) {
            return true;
        }
        int dowValue = dow.getValue();
        return (this.daysOfWeek & (1 << dowValue)) != 0;
    }

    /**
     * Resolves the given bitmask back to the array of days of week.
     * @param dow bitmask
     * @return array with days of week, can be empty
     */
    protected DayOfWeek[] resolveDaysOfWeek(final byte dow) {
        int numEntries = Integer.bitCount(dow & BYTE_TO_INT_MASK);
        if (numEntries == 0) {
            return new DayOfWeek[0];
        }
        DayOfWeek[] dowResult = new DayOfWeek[numEntries];
        int idx = 0;
        for (int i = 1; i < Byte.SIZE; i++) {
            if ((dow & (1 << i)) != 0) {
                dowResult[idx++] = DayOfWeek.of(i);
            }
        }
        return dowResult;
    }
}
