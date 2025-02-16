package com.github.nilscoding.j8tools.predicate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Predicate to check for day of week.
 * @author NilsCoding
 */
public class DayOfWeekLocalDatePredicate extends DayOfWeekPredicateBase implements Predicate<LocalDate> {

    /**
     * Creates a new instance with given days of week.
     * @param daysOfWeek days of week
     */
    public DayOfWeekLocalDatePredicate(final DayOfWeek... daysOfWeek) {
        this.storeDaysOfWeek(daysOfWeek);
    }

    /**
     * Creates a new instance with given bit mask.
     * @param bitmask bit mask
     */
    protected DayOfWeekLocalDatePredicate(final byte bitmask) {
        this.storeDaysOfWeek(bitmask);
    }

    /**
     * Returns a predicate matching weekdays monday to friday.
     * @return predicate matching monday to friday
     */
    public static DayOfWeekLocalDatePredicate weekdays() {
        return new DayOfWeekLocalDatePredicate(BITMASK_WEEKDAYS);
    }

    /**
     * Returns a predicate matching weekends saturday and sunday.
     * @return predicate matching saturday and sunday
     */
    public static DayOfWeekLocalDatePredicate weekends() {
        return new DayOfWeekLocalDatePredicate(BITMASK_WEEKEND);
    }

    @Override
    public boolean test(LocalDate localDate) {
        if (localDate == null) {
            return false;
        }
        return this.containsDayOfWeek(localDate.getDayOfWeek());
    }

    /**
     * Returns the days of week.
     * @return days of week, can be empty
     */
    public DayOfWeek[] getDaysOfWeek() {
        return this.resolveDaysOfWeek(this.daysOfWeek);
    }
}
