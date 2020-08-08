package com.github.nilscoding.j8tools.consumer;

import java.util.function.Consumer;

/**
 * Consumer which counts how many elements have been consumed and forwards them to another consumer
 * @author NilsCoding
 * @param <T>   type of elements
 */
public class CountingConsumer<T> implements Consumer<T> {

    protected Consumer<T> parentConsumer;
    protected long count;
    
    /**
     * Creates a counting consumer
     * @param parent    parent consumer to forward to
     */
    public CountingConsumer(Consumer<T> parent) {
        this.parentConsumer = parent;
    }

    /**
     * Returns the number of consumed elements
     * @return  number of consumed elements
     */
    public long getCount() {
        return this.count;
    }
    
    @Override
    public void accept(T t) {
        this.count++;
        if (this.parentConsumer != null) {
            this.parentConsumer.accept(t);
        }
    }
    
}
