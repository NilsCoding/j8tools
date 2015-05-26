package com.github.nilscoding.j8tools.supplier;

import java.util.function.LongSupplier;

/**
 * Fibonacci Long Supplier
 * @author NilsCoding
 */
public class FibonacciLongPrimitiveSupplier implements LongSupplier {

    protected long a = 1;
    protected long b = 0;
    
    /**
     * Creates a Fibonacci supplier
     */
    public FibonacciLongPrimitiveSupplier() {
    }
    
    @Override
    public long getAsLong() {
        long c = this.a + this.b;
        this.a = this.b;
        this.b = c;
        return c;
    }
    
}
