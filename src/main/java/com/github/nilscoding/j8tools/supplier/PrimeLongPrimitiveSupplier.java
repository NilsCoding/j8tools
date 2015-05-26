package com.github.nilscoding.j8tools.supplier;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.LongSupplier;

/**
 * Supplier returning only prime numbers
 * @author Nils
 */
public class PrimeLongPrimitiveSupplier implements LongSupplier {

    protected int certainty = 15;
    protected BigInteger step = new BigInteger("2");
    protected BigInteger value = new BigInteger("99");
    protected LinkedList<Long> cache = new LinkedList<>(Arrays.asList(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L, 37L, 41L, 43L, 47L, 53L, 59L, 61L, 67L, 71L, 73L, 79L, 83L, 89L, 97L));
    
    /**
     * Creates a new prime supplier (certainty 15)
     */
    public PrimeLongPrimitiveSupplier() {
    }
    
    /**
     * Creates a new prime supplier with given certainty
     * @param certainty     certainty
     */
    public PrimeLongPrimitiveSupplier(int certainty) {
        this.certainty = certainty;
        if (this.certainty < 1) {
            this.certainty = 10;
        }
    }
    
    @Override
    public long getAsLong() {
        if (this.cache.isEmpty() == false) {
            return this.cache.poll();
        }
        do {
            this.value = this.value.add(this.step);
        } while (this.value.isProbablePrime(this.certainty) == false);
        return this.value.longValue();
    }
    
}
