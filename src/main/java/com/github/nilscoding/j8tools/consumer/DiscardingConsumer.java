package com.github.nilscoding.j8tools.consumer;

import java.util.function.Consumer;

/**
 * Consumer which discards all consumed elements, so basically just a dummy
 * @author NilsCoding
 * @param <T>   type of consumer
 */
public class DiscardingConsumer<T> implements Consumer<T> {

    /**
     * Creates a discarding consumer
     */
    public DiscardingConsumer() {
    }
    
    @Override
    public void accept(T t) {
    }
    
}
