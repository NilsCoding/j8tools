package com.github.nilscoding.j8tools.supplier;

import java.util.function.Supplier;

/**
 * Repeats the given elements over and over again.
 * Please note that no references to the objects are returned, but the objects itself (so their references cannot
 * be changed, but their internal values can).
 * @param <T> type of elements
 * @author NilsCoding
 */
public class RepeatingSupplier<T> implements Supplier<T> {

    /**
     * Elements.
     */
    protected T[] elements;
    /**
     * Index.
     */
    protected int index = -1;

    /**
     * Creates a new repeating supplier based on given elements.
     * @param elements elements to repeat over
     */
    public RepeatingSupplier(final T... elements) {
        this.elements = elements;
    }

    @Override
    public T get() {
        if ((this.elements == null) || (this.elements.length == 0)) {
            return null;
        }
        this.index++;
        if (this.index >= this.elements.length) {
            this.index = 0;
        }
        return this.elements[this.index];
    }

}
