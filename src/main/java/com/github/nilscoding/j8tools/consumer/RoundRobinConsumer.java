package com.github.nilscoding.j8tools.consumer;

import java.util.function.Consumer;

/**
 * Consumer which distributes elements to other consumers.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class RoundRobinConsumer<T> implements Consumer<T> {

    /**
     * Consumers.
     */
    protected Consumer<? super T>[] consumers;
    /**
     * Number of consumers.
     */
    protected int numConsumers = 0;
    /**
     * Current consumer index.
     */
    protected int consumerIndex = -1;

    /**
     * Creates a new Round-Robin Consumer.
     * @param consumers consumers to forward to
     */
    public RoundRobinConsumer(final Consumer<? super T>... consumers) {
        this.consumers = consumers;
        if (this.consumers != null) {
            this.numConsumers = this.consumers.length;
        }
    }

    @Override
    public void accept(T t) {
        if (this.numConsumers == 0) {
            return;
        }
        this.consumerIndex++;
        if (this.consumerIndex >= this.numConsumers) {
            this.consumerIndex = 0;
        }
        Consumer<? super T> consumer = this.consumers[this.consumerIndex];
        if (consumer != null) {
            consumer.accept(t);
        }
    }

}
