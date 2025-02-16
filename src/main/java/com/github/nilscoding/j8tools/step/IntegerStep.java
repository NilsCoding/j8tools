package com.github.nilscoding.j8tools.step;

/**
 * Integer step.
 * @author NilsCoding
 */
public final class IntegerStep implements Step<Class<Integer>, Integer, Integer, IntegerStep> {

    /**
     * Step value.
     */
    private final int stepValue;

    /**
     * Creates a new instance with given step value.
     * @param stepValue step value
     */
    public IntegerStep(final int stepValue) {
        this.stepValue = stepValue;
    }

    /**
     * Convenient method to return a step value 1.
     * @return instance with step value 1
     */
    public static IntegerStep one() {
        return new IntegerStep(1);
    }

    @Override
    public Class<Integer> getUnit() {
        return Integer.class;
    }

    @Override
    public Integer getAmount() {
        return this.stepValue;
    }

    @Override
    public Integer add(Integer obj) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException();
        }
        return (obj + this.stepValue);
    }

    @Override
    public Integer subtract(Integer obj) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException();
        }
        return (obj - this.stepValue);
    }

    @Override
    public IntegerStep inverse() {
        return new IntegerStep(this.stepValue * -1);
    }

    @Override
    public IntegerStep copy() {
        return new IntegerStep(this.stepValue);
    }
}
