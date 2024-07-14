package com.github.nilscoding.j8tools.supplier;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.function.Supplier;

/**
 * Supplier which returns increasing ZonedDateTimes.
 * @author NilsCoding
 */
public class IncreasingZonedDateTimeSupplier implements Supplier<ZonedDateTime> {

    /**
     * Date time.
     */
    protected ZonedDateTime dt;
    /**
     * Temporal amount.
     */
    protected TemporalAmount temporalAmount;

    /**
     * Creates a new supplier with given start time and temporal amount to increase.
     * @param startDateTime start time, null for now
     * @param increaseBy    increase by, null for 1 minute
     */
    public IncreasingZonedDateTimeSupplier(final ZonedDateTime startDateTime, final TemporalAmount increaseBy) {
        this.dt = startDateTime;
        if (this.dt == null) {
            this.dt = ZonedDateTime.now();
        }
        this.temporalAmount = increaseBy;
        if (this.temporalAmount == null) {
            this.temporalAmount = Duration.ofMinutes(1L);
        }
    }

    @Override
    public ZonedDateTime get() {
        ZonedDateTime result = this.dt;
        this.dt = this.dt.plus(this.temporalAmount);
        return result;
    }

}
