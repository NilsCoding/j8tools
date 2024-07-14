package com.github.nilscoding.j8tools.consumer;

import java.util.function.Consumer;

/**
 * Consumer which forwards each consumed object to the given consumers.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class SplittingConsumer<T> implements Consumer<T> {

    /**
     * Consumers.
     */
    protected Consumer<? super T>[] consumers = null;

    /**
     * Creates a splitting consumer which will forward to all given consumers.
     * @param consumers consumers to forward to
     */
    public SplittingConsumer(final Consumer<? super T>... consumers) {
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
