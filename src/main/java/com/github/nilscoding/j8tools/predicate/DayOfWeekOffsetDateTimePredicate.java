package com.github.nilscoding.j8tools.predicate;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.function.Predicate;

/**
 * Predicate to check for day of week.
 * @author NilsCoding
 */
public class DayOfWeekOffsetDateTimePredicate extends DayOfWeekPredicateBase implements Predicate<OffsetDateTime> {

    /**
     * Creates a new instance with given days of week.
     * @param daysOfWeek days of week
     */
    public DayOfWeekOffsetDateTimePredicate(final DayOfWeek... daysOfWeek) {
        this.storeDaysOfWeek(daysOfWeek);
    }

    /**
     * Creates a new instance with given bit mask.
     * @param bitmask bit mask
     */
    protected DayOfWeekOffsetDateTimePredicate(final byte bitmask) {
        this.storeDaysOfWeek(bitmask);
    }

    /**
     * Returns a predicate matching weekdays monday to friday.
     * @return predicate matching monday to friday
     */
    public static DayOfWeekOffsetDateTimePredicate weekdays() {
        return new DayOfWeekOffsetDateTimePredicate(BITMASK_WEEKDAYS);
    }

    /**
     * Returns a predicate matching weekends saturday and sunday.
     * @return predicate matching saturday and sunday
     */
    public static DayOfWeekOffsetDateTimePredicate weekends() {
        return new DayOfWeekOffsetDateTimePredicate(BITMASK_WEEKEND);
    }

    @Override
    public boolean test(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return false;
        }
        return this.containsDayOfWeek(offsetDateTime.getDayOfWeek());
    }

    /**
     * Returns the days of week.
     * @return days of week, can be empty
     */
    public DayOfWeek[] getDaysOfWeek() {
        return this.resolveDaysOfWeek(this.daysOfWeek);
    }
}
