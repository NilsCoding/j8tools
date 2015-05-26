package com.github.nilscoding.j8tools.consumer;

import java.util.Random;
import java.util.function.Consumer;

/**
 * Consumer which randomly distributes the elements to other consumers
 * @author NilsCoding
 * @param <T>   type of consumer
 */
public class RandomDistributionConsumer<T> implements Consumer<T> {

    protected Consumer<? super T>[] consumers;
    protected int numConsumers = 0;
    protected Random rnd = new Random(System.currentTimeMillis());

    /**
     * Creates a random distribution consumer
     * @param consumers     consumers to forward to
     */
    public RandomDistributionConsumer(Consumer<? super T> ... consumers) {
        this.consumers = consumers;
        if (this.consumers != null) {
            this.numConsumers = this.consumers.length;
        }
    }
    
    /**
     * Creates a random distribution consumer
     * @param rnd   random implementation to use for distribution
     * @param consumers     consumers to forward to
     */
    public RandomDistributionConsumer(Random rnd, Consumer<? super T> ... consumers) {
        this.consumers = consumers;
        if (rnd != null) {
            this.rnd = rnd;
        }
        if (this.consumers != null) {
            this.numConsumers = this.consumers.length;
        }
    }
    
    @Override
    public void accept(T t) {
        if (this.numConsumers == 0) {
            return;
        }
        Consumer<? super T> consumer = this.consumers[this.rnd.nextInt(this.numConsumers)];
        if (consumer != null) {
            consumer.accept(t);
        }
    }
    
}
