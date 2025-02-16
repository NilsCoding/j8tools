package com.github.nilscoding.j8tools.step;

import java.util.function.Supplier;

/**
 * Integer steppable.
 * @param <S> type of step
 * @author NilsCoding
 */
public final class IntegerSteppable<S extends Step<Class<Integer>, Integer, Integer, S>>
        implements Steppable<Integer,
        Class<Integer>,
        Integer,
        S,
        IntegerSteppable<S>> {

    /**
     * Initial start value.
     */
    private final int startedAt;

    /**
     * Current value.
     */
    private int value;
    /**
     * Step size.
     */
    private final S stepSize;

    /**
     * Creates a new instance with given value.
     * @param startedAt     start value
     * @param i             value
     * @param stepSizeValue step size value
     */
    @SuppressWarnings("unchecked")
    private IntegerSteppable(int startedAt, int i, int stepSizeValue) {
        this.startedAt = startedAt;
        this.value = i;
        this.stepSize = (S) new IntegerStep(stepSizeValue);
    }

    /**
     * Creates a new instance with given value.
     * @param startedAt start value
     * @param i         value
     * @param stepSize  step size
     */
    private IntegerSteppable(int startedAt, int i, S stepSize) {
        this.startedAt = startedAt;
        this.value = i;
        this.stepSize = stepSize;
    }

    /**
     * Creates an instance with given value and step size 1.
     * @param i   value
     * @param <S> type of step
     * @return instance
     */
    public static <S extends Step<Class<Integer>, Integer, Integer, S>> IntegerSteppable<S> of(int i) {
        return new IntegerSteppable<>(i, i, 1);
    }

    /**
     * Creates an instance with given value and given step size.
     * @param i        value
     * @param stepSize step size
     * @param <S>      type of step
     * @return instance
     */
    public static <S extends Step<Class<Integer>, Integer, Integer, S>> IntegerSteppable<S> of(int i, int stepSize) {
        return new IntegerSteppable<>(i, i, stepSize);
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public S getStepSize() {
        return this.stepSize;
    }

    @Override
    public IntegerSteppable<S> forward() {
        this.value = this.stepSize.add(this.value);
        return this;
    }

    @Override
    public IntegerSteppable<S> backward() {
        this.value = this.stepSize.subtract(this.value);
        return this;
    }

    @Override
    public IntegerSteppable<S> inverse() {
        return new IntegerSteppable<>(this.value, this.value, this.stepSize.inverse());
    }

    @Override
    public IntegerSteppable<S> fromStart() {
        return new IntegerSteppable<>(this.startedAt, this.startedAt, this.stepSize.copy());
    }

    @Override
    public Supplier<Integer> asSupplier() {
        return new SteppableSupplier<>(this);
    }

    @Override
    public int compareTo(Steppable<Integer, Class<Integer>, Integer, S, IntegerSteppable<S>> other) {
        if (other == null) {
            throw new NullPointerException();
        }
        return (this.value - other.getValue());
    }

    @Override
    public String toString() {
        return "IntegerSteppable{value=" + this.value + "}";
    }

}
