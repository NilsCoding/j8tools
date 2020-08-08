package com.github.nilscoding.j8tools.supplier;

import java.util.function.Supplier;

/**
 * Repeats the given elements over and over again
 * @author NilsCoding
 */
public class RepeatingSupplier<T> implements Supplier<T> {

    protected T[] elements;
    protected int index = -1;
    
    public RepeatingSupplier(T... elements) {
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
