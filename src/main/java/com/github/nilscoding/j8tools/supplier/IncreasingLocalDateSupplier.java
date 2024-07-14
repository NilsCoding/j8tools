package com.github.nilscoding.j8tools.supplier;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Supplier;

/**
 * Supplier which returns increasing LocalDates.
 * @author NilsCoding
 */
public class IncreasingLocalDateSupplier implements Supplier<LocalDate> {

    /**
     * Date.
     */
    protected LocalDate dt;
    /**
     * Period.
     */
    protected Period period;

    /**
     * Creates a new supplier with given start time and temporal amount to increase.
     * @param startDateTime start time, null for now
     * @param increaseBy    increase by, null for 1 day
     */
    public IncreasingLocalDateSupplier(final LocalDate startDateTime, final Period increaseBy) {
        this.dt = startDateTime;
        if (this.dt == null) {
            this.dt = LocalDate.now();
        }
        this.period = increaseBy;
        if (this.period == null) {
            this.period = Period.ofDays(1);
        }
    }

    @Override
    public LocalDate get() {
        LocalDate result = this.dt;
        this.dt = this.dt.plus(this.period);
        return result;
    }

}
