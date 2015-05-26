package com.github.nilscoding.j8tools.consumer;

import java.util.Random;
import java.util.function.Consumer;

/**
 * Consumer which forwards to another consumer, but randomly discards elements
 * @author NilsCoding
 * @param <T>   type of consumer
 */
public class RandomlyDiscardingConsumer<T> implements Consumer<T> {

    protected Consumer<T> parentConsumer;
    protected Random rnd = new Random(System.currentTimeMillis());
    
    /**
     * Creates a randomly discarding consumer
     * @param parent    parent consumer to forward to
     */
    public RandomlyDiscardingConsumer(Consumer<T> parent) {
        this.parentConsumer = parent;
        this.rnd = new Random(System.currentTimeMillis());
    }
    
    /**
     * Creates a randomly discarding consumer
     * @param random    random implementation
     * @param parent    parent consumer to forward to
     */
    public RandomlyDiscardingConsumer(Random random, Consumer<T> parent) {
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
