package com.github.nilscoding.j8tools.step;

import java.util.function.Supplier;

/**
 * Supplier for Steppable.
 * @param <T> type of supplied elements
 * @author NilsCoding
 */
class SteppableSupplier<T> implements Supplier<T> {

    /**
     * Parent Steppable.
     */
    private final Steppable<T, ?, ?, ?, ?> steppable;

    /**
     * First value.
     */
    private T firstValue;

    /**
     * Creates a new Supplier instance for given Steppable.
     * @param steppable parent steppable
     */
    SteppableSupplier(final Steppable<T, ?, ?, ?, ?> steppable) {
        this.steppable = steppable;
        this.firstValue = this.steppable.getValue();
    }

    @Override
    public T get() {
        if (this.firstValue != null) {
            T tmpValue = this.firstValue;
            this.firstValue = null;
            return tmpValue;
        }
        return this.steppable.forward().getValue();
    }
}
