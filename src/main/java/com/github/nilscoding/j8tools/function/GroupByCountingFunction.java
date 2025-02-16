package com.github.nilscoding.j8tools.function;

import java.util.function.Function;

/**
 * A function that returns a "group" value by counting the seen elements and increases the returned value
 * each n-th element. Result will start with 0.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class GroupByCountingFunction<T> implements Function<T, Integer> {

    /**
     * Current counting value.
     */
    protected int count = 0;
    /**
     * Group size.
     */
    protected int groupSize;
    /**
     * Count of seen elements in current group.
     */
    protected int groupCount = 0;

    /**
     * Creates a new instance with given group size. Group size cannot be 0 or less, will use 1 in such cases.
     * @param groupSize group size
     */
    public GroupByCountingFunction(final int groupSize) {
        this.groupSize = Math.max(1, groupSize);
    }

    /**
     * Creates a new instance with given group size and initial value. Group size cannot be 0 or less, will use 1 in
     * such cases. Initial value can be any value.
     * @param groupSize    group size
     * @param initialValue initial value
     */
    public GroupByCountingFunction(final int groupSize, final int initialValue) {
        this.groupSize = Math.max(1, groupSize);
        this.count = initialValue;
    }

    /**
     * Returns the count. This is the value that was last returned when {@link #apply(Object)} was invoked.
     * @return last value
     */
    public int getCount() {
        return this.count;
    }

    @Override
    public Integer apply(T t) {
        this.groupCount++;
        if (this.groupCount > this.groupSize) {
            this.groupCount = 1;
            this.count++;
        }
        return this.count;
    }

}
