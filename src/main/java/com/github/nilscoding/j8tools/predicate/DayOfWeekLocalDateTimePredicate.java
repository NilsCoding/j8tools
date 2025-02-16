package com.github.nilscoding.j8tools.predicate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Predicate to check for day of week.
 * @author NilsCoding
 */
public class DayOfWeekLocalDateTimePredicate extends DayOfWeekPredicateBase implements Predicate<LocalDateTime> {

    /**
     * Creates a new instance with given days of week.
     * @param daysOfWeek days of week
     */
    public DayOfWeekLocalDateTimePredicate(final DayOfWeek... daysOfWeek) {
        this.storeDaysOfWeek(daysOfWeek);
    }

    /**
     * Creates a new instance with given bit mask.
     * @param bitmask bit mask
     */
    protected DayOfWeekLocalDateTimePredicate(final byte bitmask) {
        this.storeDaysOfWeek(bitmask);
    }

    /**
     * Returns a predicate matching weekdays monday to friday.
     * @return predicate matching monday to friday
     */
    public static DayOfWeekLocalDateTimePredicate weekdays() {
        return new DayOfWeekLocalDateTimePredicate(BITMASK_WEEKDAYS);
    }

    /**
     * Returns a predicate matching weekends saturday and sunday.
     * @return predicate matching saturday and sunday
     */
    public static DayOfWeekLocalDateTimePredicate weekends() {
        return new DayOfWeekLocalDateTimePredicate(BITMASK_WEEKEND);
    }

    @Override
    public boolean test(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return false;
        }
        return this.containsDayOfWeek(localDateTime.getDayOfWeek());
    }

    /**
     * Returns the days of week.
     * @return days of week, can be empty
     */
    public DayOfWeek[] getDaysOfWeek() {
        return this.resolveDaysOfWeek(this.daysOfWeek);
    }
}
