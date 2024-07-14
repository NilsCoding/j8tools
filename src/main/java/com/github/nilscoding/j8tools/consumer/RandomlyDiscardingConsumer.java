package com.github.nilscoding.j8tools.consumer;

import java.util.Random;
import java.util.function.Consumer;

/**
 * Consumer which forwards to another consumer, but randomly discards elements.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class RandomlyDiscardingConsumer<T> implements Consumer<T> {

    /**
     * Consumer.
     */
    protected Consumer<T> parentConsumer;
    /**
     * Random.
     */
    protected Random rnd = new Random(System.currentTimeMillis());

    /**
     * Creates a randomly discarding consumer.
     * @param parent parent consumer to forward to
     */
    public RandomlyDiscardingConsumer(final Consumer<T> parent) {
        this.parentConsumer = parent;
        this.rnd = new Random(System.currentTimeMillis());
    }

    /**
     * Creates a randomly discarding consumer.
     * @param random random implementation
     * @param parent parent consumer to forward to
     */
    public RandomlyDiscardingConsumer(final Random random, final Consumer<T> parent) {
        this.parentConsumer = parent;
        if (random != null) {
            this.rnd = random;
        }
    }

    @Override
    public void accept(T t) {
        if (this.parentConsumer == null) {
            return;
        }
        if (this.rnd.nextBoolean() == true) {
            this.parentConsumer.accept(t);
        }
    }

}
