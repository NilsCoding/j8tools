package com.github.nilscoding.j8tools.supplier;

import java.util.function.Supplier;

/**
 * Supplier which returns the given instance over and over again.<br>
 * Please note that while the instance cannot be changed, the object might be modified internally.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class InfiniteInstanceSupplier<T> implements Supplier<T> {

    /**
     * Object to supply.
     */
    protected T object;

    /**
     * Creates a supplier providing null.
     */
    public InfiniteInstanceSupplier() {
    }

    /**
     * Creates a supplier providing the given object.
     * @param object object to return, can be null
     */
    public InfiniteInstanceSupplier(final T object) {
        this.object = object;
    }

    @Override
    public T get() {
        return object;
    }

}
