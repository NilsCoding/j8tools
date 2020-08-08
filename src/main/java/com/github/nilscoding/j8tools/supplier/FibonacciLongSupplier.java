package com.github.nilscoding.j8tools.supplier;

import java.util.function.Supplier;

/**
 * Fibonacci Long Supplier
 * @author NilsCoding
 */
public class FibonacciLongSupplier implements Supplier<Long> {

    protected int n = 0;
    protected long a = 0;
    protected long b = 1;
    
    /**
     * Creates a Fibonacci supplier
     */
    public FibonacciLongSupplier() {
    }
    
    @Override
    public Long get() {
        if (this.n == 0) {
            this.n = 1;
            return this.a;
        }
        if (this.n == 1) {
            this.n = 2;
            return this.b;
        }
        long c = this.a + this.b;
        this.a = this.b;
        this.b = c;
        return c;
    }
    
}
