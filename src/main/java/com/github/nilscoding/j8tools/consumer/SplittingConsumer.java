package com.github.nilscoding.j8tools.consumer;

import java.util.function.Consumer;

/**
 * Consumer which forwards each consumed object to the given consumers
 * @author NilsCoding
 * @param <T>   type of elements
 */
public class SplittingConsumer<T> implements Consumer<T> {

    protected Consumer<? super T>[] consumers = null;
    
    /**
     * Creates a spliiting consumer which will forward to all given consumers
     * @param consumers     consumers to forward to
     */
    public SplittingConsumer(Consumer<? super T> ... consumers) {
        this.consumers = consumers;
    }
    
    @Override
    public void accept(T t) {
        if (this.consumers != null) {
            for (Consumer<? super T> consumer : this.consumers) {
                if (consumer != null) {
                    consumer.accept(t);
                }
            }
        }
    }
    
}
