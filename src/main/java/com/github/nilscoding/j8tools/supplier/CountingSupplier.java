package com.github.nilscoding.j8tools.supplier;

import java.util.function.Supplier;

/**
 * Decorator that counts how many elements have been supplied
 * @author NilsCoding
 * @param <T>   type of supplier
 */
public class CountingSupplier<T> implements Supplier<T> {

    protected Supplier<T> parent;
    protected long count = 0;
    
    /**
     * Creates a new counting supplier
     * @param parentSupplier    parent supplier
     */
    public CountingSupplier(Supplier<T> parentSupplier) {
        if (parentSupplier == null) {
            throw new IllegalArgumentException("Parent supplier cannot be null.");
        }
        this.parent = parentSupplier;
    }

    /**
     * Returns the number of counted supply invocations
     * @return  number of invocations
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
