package com.github.nilscoding.j8tools.supplier;

import java.util.function.Supplier;

/**
 * Special purpose supplier which calls the parent supplier and provides the
 * same value for a given number of times.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class DelayingSupplier<T> implements Supplier<T> {

    /**
     * Value.
     */
    protected T value;
    /**
     * Counter.
     */
    protected int count = -1;
    /**
     * Delay count.
     */
    protected int delayCount;
    /**
     * Supplier.
     */
    protected Supplier<T> parentSupplier;

    /**
     * Creates a new delaying supplier.
     * @param parentSupplier parent supplier
     * @param delayCount     number of times that the same result will be returned
     */
    public DelayingSupplier(final Supplier<T> parentSupplier, final int delayCount) {
        this.parentSupplier = parentSupplier;
        if (this.parentSupplier == null) {
            throw new IllegalArgumentException("Parent supplier cannot be null.");
        }
        this.delayCount = delayCount;
        if (this.delayCount < 0) {
            this.delayCount = 0;
        }
        this.count = this.delayCount + 1;
    }

    @Override
    public T get() {
        this.count++;
        if (this.count >= this.delayCount) {
            this.value = this.parentSupplier.get();
            this.count = 0;
        }
        return this.value;
    }

}
