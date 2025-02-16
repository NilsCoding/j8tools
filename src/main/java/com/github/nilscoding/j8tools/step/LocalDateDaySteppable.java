package com.github.nilscoding.j8tools.step;

import java.time.LocalDate;
import java.util.function.Supplier;

/**
 * LocalDate day steppable.
 * @param <S> type of step
 * @author NilsCoding
 */
public final class LocalDateDaySteppable<S extends Step<Class<?>, Integer, LocalDate, S>>
        implements Steppable<LocalDate,
        Class<?>,
        Integer,
        S,
        LocalDateDaySteppable<S>> {

    /**
     * Initial start value.
     */
    private final LocalDate startedAt;
    /**
     * Current value.
     */
    private LocalDate value;
    /**
     * Step size.
     */
    private final S stepSize;

    /**
     * Creates a new instance with given value.
     * @param startedAt     start value
     * @param ld            value
     * @param stepSizeValue step size value
     */
    @SuppressWarnings("unchecked")
    private LocalDateDaySteppable(LocalDate startedAt, LocalDate ld, int stepSizeValue) {
        this.startedAt = startedAt;
        this.value = ld;
        this.stepSize = (S) new LocalDateDayStep(stepSizeValue);
    }

    /**
     * Creates a new instance with given value.
     * @param startedAt start value
     * @param ld        value
     * @param stepSize  step size
     */
    private LocalDateDaySteppable(LocalDate startedAt, LocalDate ld, S stepSize) {
        this.startedAt = startedAt;
        this.value = ld;
        this.stepSize = stepSize;
    }

    /**
     * Creates an instance with given value.
     * @param ld  value
     * @param <S> type of step
     * @return instance
     * @throws NullPointerException thrown if given parameter is null
     */
    public static <S extends Step<Class<?>, Integer, LocalDate, S>> LocalDateDaySteppable<S> of(LocalDate ld) {
        if (ld == null) {
            throw new NullPointerException();
        }
        return new LocalDateDaySteppable<>(ld, ld, 1);
    }

    /**
     * Creates an instance with given value and step size.
     * @param ld            value
     * @param stepSizeValue step size in days
     * @param <S>           type of step
     * @return instance
     * @throws NullPointerException thrown if given parameter is null
     */
    public static <S extends Step<Class<?>, Integer, LocalDate, S>> LocalDateDaySteppable<S> of(LocalDate ld,
                                                                                                int stepSizeValue) {
        if (ld == null) {
            throw new NullPointerException();
        }
        return new LocalDateDaySteppable<>(ld, ld, stepSizeValue);
    }

    @Override
    public LocalDate getValue() {
        return this.value;
    }

    @Override
    public S getStepSize() {
        return this.stepSize;
    }

    @Override
    public LocalDateDaySteppable<S> forward() {
        this.value = this.stepSize.add(this.value);
        return this;
    }

    @Override
    public LocalDateDaySteppable<S> backward() {
        this.value = this.stepSize.subtract(this.value);
        return this;
    }

    @Override
    public LocalDateDaySteppable<S> inverse() {
        return new LocalDateDaySteppable<>(this.value, this.value, this.stepSize.inverse());
    }

    @Override
    public LocalDateDaySteppable<S> fromStart() {
        return new LocalDateDaySteppable<>(this.startedAt, this.startedAt, this.stepSize.copy());
    }

    @Override
    public Supplier<LocalDate> asSupplier() {
        return new SteppableSupplier<>(this);
    }

    @Override
    public int compareTo(Steppable<LocalDate, Class<?>, Integer, S, LocalDateDaySteppable<S>> other) {
        if (other == null) {
            throw new NullPointerException();
        }
        return (this.value.compareTo(other.getValue()));
    }

    @Override
    public String toString() {
        return "LocalDateDaySteppable{" + this.value + "}";
    }
}
