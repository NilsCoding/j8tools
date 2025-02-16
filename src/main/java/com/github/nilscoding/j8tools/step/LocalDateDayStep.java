package com.github.nilscoding.j8tools.step;

import java.time.LocalDate;

/**
 * LocalDate day step.
 * @author NilsCoding
 */
public final class LocalDateDayStep implements Step<Class<?>, Integer, LocalDate, LocalDateDayStep> {

    /**
     * Step value in days.
     */
    private final int stepValueInDays;

    /**
     * Creates a new step with given amount.
     * @param stepValueInDays amount in days
     */
    public LocalDateDayStep(int stepValueInDays) {
        this.stepValueInDays = stepValueInDays;
    }

    @Override
    public Class<?> getUnit() {
        return null;
    }

    @Override
    public Integer getAmount() {
        return 0;
    }

    @Override
    public LocalDate add(LocalDate obj) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj.plusDays(this.stepValueInDays);
    }

    @Override
    public LocalDate subtract(LocalDate obj) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj.minusDays(this.stepValueInDays);
    }

    @Override
    public LocalDateDayStep inverse() {
        return new LocalDateDayStep(this.stepValueInDays * -1);
    }

    @Override
    public LocalDateDayStep copy() {
        return new LocalDateDayStep(this.stepValueInDays);
    }
}
