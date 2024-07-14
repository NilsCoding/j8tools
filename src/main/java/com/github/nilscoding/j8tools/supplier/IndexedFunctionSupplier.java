package com.github.nilscoding.j8tools.supplier;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Supplier which calls the given parent function with the current invocation
 * index (starting at 1) to generate a value.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class IndexedFunctionSupplier<T> implements Supplier<T> {

    /**
     * Index.
     */
    protected long index = 1L;
    /**
     * Supplying function.
     */
    protected Function<Long, T> supplyingFnc;

    /**
     * Creates a new supplier with given function, will be called with 1-based
     * index of invocation count.
     * @param supplyingFunction supplying function
     */
    public IndexedFunctionSupplier(final Function<Long, T> supplyingFunction) {
        if (supplyingFunction == null) {
            throw new IllegalArgumentException("Parent function cannot be null.");
        }
        this.supplyingFnc = supplyingFunction;
    }

    /**
     * Creates a new supplier with given function, will be called with x-based
     * index of invocation count.
     * @param supplyingFunction supplying function
     * @param startIndex        start index
     */
    public IndexedFunctionSupplier(final Function<Long, T> supplyingFunction, final long startIndex) {
        this.index = startIndex;
        if (supplyingFunction == null) {
            throw new IllegalArgumentException("Parent function cannot be null.");
        }
        this.supplyingFnc = supplyingFunction;

    }

    @Override
    public T get() {
        T value = this.supplyingFnc.apply(this.index);
        this.index++;
        return value;
    }

}
