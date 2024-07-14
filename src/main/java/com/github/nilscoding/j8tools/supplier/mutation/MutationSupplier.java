package com.github.nilscoding.j8tools.supplier.mutation;

import java.util.function.Supplier;

/**
 * Supplier to provide subsequent mutations using given mutator.
 * @param <T> type of elements
 * @param <I> type of temporary elements
 * @param <R> type of result elements
 * @author NilsCoding
 */
public class MutationSupplier<T, I, R extends T> implements Supplier<T> {

    /**
     * Mutator instance.
     */
    protected Mutator<T, I, R> mutator;

    /**
     * Creates a new supplier, using the given mutator instance.
     * @param mutator mutator instance
     */
    public MutationSupplier(final Mutator<T, I, R> mutator) {
        this.mutator = mutator;
    }

    @Override
    public R get() {
        if (this.mutator == null) {
            return null;
        }
        R nextElem = this.mutator.createNextElement();
        return nextElem;
    }

}
