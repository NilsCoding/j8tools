package com.github.nilscoding.j8tools.supplier;

import java.util.function.Supplier;

/**
 * Fibonacci Long Supplier
 * @author NilsCoding
 */
public class FibonacciLongSupplier implements Supplier<Long> {

    protected long a = 1;
    protected long b = 0;
    
    /**
     * Creates a Fibonacci supplier
     */
    public FibonacciLongSupplier() {
    }
    
    @Override
    public Long get() {
        long c = this.a + this.b;
        this.a = this.b;
        this.b = c;
        return c;
    }
    
}
