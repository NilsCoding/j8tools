package com.github.nilscoding.j8tools.supplier;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Supplier which mutates the value using a function before returning a value.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class ValueMutatingSupplier<T> implements Supplier<T> {

    /**
     * Value.
     */
    protected T value;
    /**
     * Initial value supplier.
     */
    protected Supplier<T> initialValueSuppl;
    /**
     * Mutation function.
     */
    protected Function<T, T> mutationFnc;
    /**
     * Flag to indicate initialization.
     */
    protected boolean inited = false;

    /**
     * Creates a new value-mutating supplier.
     * @param initialValueSupplier supplier for initial value
     * @param mutationFunction     mutation function
     */
    public ValueMutatingSupplier(final Supplier<T> initialValueSupplier, final Function<T, T> mutationFunction) {
        this.initialValueSuppl = initialValueSupplier;
        if (this.initialValueSuppl == null) {
            this.initialValueSuppl = () -> null;
        }
        this.mutationFnc = mutationFunction;
        if (this.mutationFnc == null) {
            this.mutationFnc = Function.identity();
        }
    }

    @Override
    public T get() {
        if (this.inited == false) {
            this.inited = true;
            this.value = this.initialValueSuppl.get();
        } else {
            this.value = this.mutationFnc.apply(this.value);
        }
        return this.value;
    }

}
