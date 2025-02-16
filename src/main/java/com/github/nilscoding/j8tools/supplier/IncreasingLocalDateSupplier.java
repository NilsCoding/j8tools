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
     * Creates a new supplier starting today and increases by one day.
     */
    public IncreasingLocalDateSupplier() {
        this.dt = LocalDate.now();
        this.period = Period.ofDays(1);
    }

    /**
     * Creates a new supplier starting with given day and increases by one day.
     * @param dt start date
     */
    public IncreasingLocalDateSupplier(LocalDate dt) {
        this.dt = dt;
        if (this.dt == null) {
            this.dt = LocalDate.now();
        }
    }

    /**
     * Creates a new supplier with given start time and temporal amount to increase.
     * @param startDte   start date, null for now
     * @param increaseBy increase by, null for 1 day
     */
    public IncreasingLocalDateSupplier(final LocalDate startDte, final Period increaseBy) {
        this.dt = startDte;
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
