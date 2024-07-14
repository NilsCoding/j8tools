package com.github.nilscoding.j8tools.supplier;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Supplier which merges the result of two suppliers using a given function.
 * @param <T> type of first supplied elements
 * @param <U> type of second supplied elements
 * @param <R> type of provided (merged) elements
 * @author NilsCoding
 */
public class MergingSupplier<T, U, R> implements Supplier<R> {

    /**
     * First supplier.
     */
    protected Supplier<T> firstSupplier;
    /**
     * Second supplier.
     */
    protected Supplier<U> secondSupplier;
    /**
     * Merging function.
     */
    protected BiFunction<T, U, R> mergeFunction;

    /**
     * Creates a new supplier with given source suppliers and a merge function.
     * @param firstSupplier  first supplier
     * @param secondSupplier second supplier
     * @param mergeFunction  merge function
     */
    public MergingSupplier(final Supplier<T> firstSupplier,
                           final Supplier<U> secondSupplier,
                           final BiFunction<T, U, R> mergeFunction) {
        this.firstSupplier = firstSupplier;
        if (this.firstSupplier == null) {
            throw new IllegalArgumentException("First supplier cannot be null.");
        }
        this.secondSupplier = secondSupplier;
        if (this.secondSupplier == null) {
            throw new IllegalArgumentException("Second supplier cannot be null.");
        }
        this.mergeFunction = mergeFunction;
        if (this.mergeFunction == null) {
            throw new IllegalArgumentException("Merge function cannot be null.");
        }
    }

    @Override
    public R get() {
        T firstElem = this.firstSupplier.get();
        U secondElem = this.secondSupplier.get();
        R resultElem = this.mergeFunction.apply(firstElem, secondElem);
        return resultElem;
    }

}
