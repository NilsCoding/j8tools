package com.github.nilscoding.j8tools.supplier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 * Supplier which returns the given objects in a Round-Robin fashion.<br>
 * Please note that the given collection should not be altered after being provided to
 * this Supplier (which however will try to handle this as gracefully as possible).
 * @param <T> type of elements
 * @author NilsCoding
 */
public class RoundRobinSupplier<T> implements Supplier<T> {

    /**
     * Source elements.
     */
    protected Collection<T> srcElements;
    /**
     * Element iterator.
     */
    protected Iterator<T> elemItr;

    /**
     * Creates a new round-robin supplier with given elements.
     * @param sourceElements source elements
     */
    public RoundRobinSupplier(final Collection<T> sourceElements) {
        this.srcElements = sourceElements;
        if (this.srcElements == null) {
            this.srcElements = new ArrayList<>(1);
        }
    }

    @Override
    public T get() {
        if (this.srcElements.isEmpty() == true) {
            return null;
        }
        if (this.elemItr == null) {
            this.elemItr = this.srcElements.iterator();
        }
        if (this.elemItr.hasNext() == false) {
            // either all elements have been consumed or the source is empty
            // anyway, we need to get a new Iterator from the collection
            this.elemItr = this.srcElements.iterator();
            if (this.elemItr.hasNext() == false) {
                return null;
            }
        }
        T tmpElem = this.elemItr.next();
        return tmpElem;
    }

}
