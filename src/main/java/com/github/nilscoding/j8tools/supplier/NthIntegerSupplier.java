package com.github.nilscoding.j8tools.supplier;

import java.util.function.Supplier;

/**
 * N-th Integer value supplier
 * @author NilsCoding
 */
public class NthIntegerSupplier implements Supplier<Integer> {

    protected int step = 1;
    protected int lastValue = -1;

    /**
     * Creates a supplier, producing a sequence starting with 0, increasing by 1
     */
    public NthIntegerSupplier() {
    }
    
    /**
     * Creates a supplier, producing a sequence starting at the given value, increasing by 1
     * @param startWith     start of sequence (included)
     */
    public NthIntegerSupplier(int startWith) {
        this.lastValue = startWith - 1;
    }
    
    /**
     * Creates a supplier, producing a sequence starting at the given value, increasing by the given value
     * @param startWith start of sequence (included)
     * @param increaseBy    increase step (can be negative)
     */
    public NthIntegerSupplier(int startWith, int increaseBy) {
        this.lastValue = startWith;
        this.step = increaseBy;
        this.lastValue = this.lastValue - increaseBy;
    }
    
    @Override
    public Integer get() {
        this.lastValue = this.lastValue + this.step;
        return this.lastValue;
    }
    
}
