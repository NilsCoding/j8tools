package com.github.nilscoding.j8tools.supplier;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAmount;
import java.util.function.Supplier;

/**
 * Supplier which returns increasing OffsetDateTimes.
 * @author NilsCoding
 */
public class IncreasingOffsetDateTimeSupplier implements Supplier<OffsetDateTime> {

    /**
     * Date time.
     */
    protected OffsetDateTime dt;
    /**
     * Temporal amount.
     */
    protected TemporalAmount temporalAmount;

    /**
     * Creates a new supplier with given start time and temporal amount to increase.
     * @param startDateTime start time, null for now
     * @param increaseBy    increase by, null for 1 minute
     */
    public IncreasingOffsetDateTimeSupplier(final OffsetDateTime startDateTime, final TemporalAmount increaseBy) {
        this.dt = startDateTime;
        if (this.dt == null) {
            this.dt = OffsetDateTime.now();
        }
        this.temporalAmount = increaseBy;
        if (this.temporalAmount == null) {
            this.temporalAmount = Duration.ofMinutes(1L);
        }
    }

    @Override
    public OffsetDateTime get() {
        OffsetDateTime result = this.dt;
        this.dt = this.dt.plus(this.temporalAmount);
        return result;
    }

}
