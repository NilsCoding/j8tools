package com.github.nilscoding.j8tools.supplier;

import java.util.function.Supplier;

/**
 * Decorator that counts how many elements have been supplied.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class CountingSupplier<T> implements Supplier<T> {

    /**
     * Supplier.
     */
    protected Supplier<T> parent;
    /**
     * Counter.
     */
    protected long count = 0;

    /**
     * Creates a new counting supplier.
     * @param parentSupplier parent supplier
     */
    public CountingSupplier(final Supplier<T> parentSupplier) {
        if (parentSupplier == null) {
            throw new IllegalArgumentException("Parent supplier cannot be null.");
        }
        this.parent = parentSupplier;
    }

    /**
     * Returns the number of counted supply invocations.
     * @return number of invocations
     */
    public long getCount() {
        return count;
    }

    @Override
    public T get() {
        T tmp = this.parent.get();
        this.count++;
        return tmp;
    }

}
