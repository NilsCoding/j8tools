package com.github.nilscoding.j8tools.facetting;

/**
 * Facet value with count.
 * @param <U> type of value
 * @author NilsCoding
 */
public class FacetValue<U> {

    /**
     * Value.
     */
    protected U value;
    /**
     * Count.
     */
    protected int count;

    /**
     * Creates a new facet value object with given value and given count.
     * @param value value
     * @param count count
     */
    public FacetValue(final U value, final int count) {
        this.value = value;
        this.count = count;
    }

    /**
     * Increases the count by 1.
     */
    public void increaseCount() {
        this.count++;
    }

    /**
     * Returns the value.
     * @return value
     */
    public U getValue() {
        return value;
    }

    /**
     * Sets the value.
     * @param value value to set
     */
    public void setValue(U value) {
        this.value = value;
    }

    /**
     * Returns the count.
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count.
     * @param count count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

}
