package com.github.nilscoding.j8tools.predicate;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.function.Predicate;

/**
 * Predicate to check for day of week.
 * @author NilsCoding
 */
public class DayOfWeekZonedDateTimePredicate extends DayOfWeekPredicateBase implements Predicate<ZonedDateTime> {

    /**
     * Creates a new instance with given days of week.
     * @param daysOfWeek days of week
     */
    public DayOfWeekZonedDateTimePredicate(final DayOfWeek... daysOfWeek) {
        this.storeDaysOfWeek(daysOfWeek);
    }

    /**
     * Creates a new instance with given bit mask.
     * @param bitmask bit mask
     */
    protected DayOfWeekZonedDateTimePredicate(final byte bitmask) {
        this.storeDaysOfWeek(bitmask);
    }

    /**
     * Returns a predicate matching weekdays monday to friday.
     * @return predicate matching monday to friday
     */
    public static DayOfWeekZonedDateTimePredicate weekdays() {
        return new DayOfWeekZonedDateTimePredicate(BITMASK_WEEKDAYS);
    }

    /**
     * Returns a predicate matching weekends saturday and sunday.
     * @return predicate matching saturday and sunday
     */
    public static DayOfWeekZonedDateTimePredicate weekends() {
        return new DayOfWeekZonedDateTimePredicate(BITMASK_WEEKEND);
    }

    @Override
    public boolean test(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) {
            return false;
        }
        return this.containsDayOfWeek(zonedDateTime.getDayOfWeek());
    }

    /**
     * Returns the days of week.
     * @return days of week, can be empty
     */
    public DayOfWeek[] getDaysOfWeek() {
        return this.resolveDaysOfWeek(this.daysOfWeek);
    }
}
