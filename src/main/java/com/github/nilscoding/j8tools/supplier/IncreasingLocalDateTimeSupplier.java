package com.github.nilscoding.j8tools.supplier;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.function.Supplier;

/**
 * Supplier which returns increasing LocalDateTimes.
 * @author NilsCoding
 */
public class IncreasingLocalDateTimeSupplier implements Supplier<LocalDateTime> {

    /**
     * Date time.
     */
    protected LocalDateTime dt;
    /**
     * Temporal amount.
     */
    protected TemporalAmount temporalAmount;

    /**
     * Creates a new supplier with given start time and temporal amount to increase.
     * @param startDateTime start time, null for now
     * @param increaseBy    increase by, null for 1 minute
     */
    public IncreasingLocalDateTimeSupplier(final LocalDateTime startDateTime, final TemporalAmount increaseBy) {
        this.dt = startDateTime;
        if (this.dt == null) {
            this.dt = LocalDateTime.now();
        }
        this.temporalAmount = increaseBy;
        if (this.temporalAmount == null) {
            this.temporalAmount = Duration.ofMinutes(1L);
        }
    }

    @Override
    public LocalDateTime get() {
        LocalDateTime result = this.dt;
        this.dt = this.dt.plus(this.temporalAmount);
        return result;
    }

}
