package com.github.nilscoding.j8tools.supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Supplier which returns a randomly chosen element from given initialization
 * collection (please note that a copy of the given element collection is made
 * so changes to do not change this supplier's collection).
 * @param <T> type of elements
 * @author NilsCoding
 */
public class RandomChoiceSupplier<T> implements Supplier<T> {

    /**
     * List with elements.
     */
    protected List<T> elementsList;
    /**
     * Random.
     */
    protected Random random;

    /**
     * Creates a new random choice supplier with normal randomness.
     * @param elements elements to choose from
     */
    public RandomChoiceSupplier(final Iterable<T> elements) {
        this(null, elements);
    }

    /**
     * Creates a new random choice supplier with normal randomness.
     * @param elements elements to choose from
     */
    public RandomChoiceSupplier(final T... elements) {
        this(null, elements);
    }


    /**
     * Creates a new random choice supplier with given randomness.
     * @param rnd      randomness
     * @param elements elements to choose from
     */
    public RandomChoiceSupplier(final Random rnd, final Iterable<T> elements) {
        this.random = (rnd != null) ? rnd : (new Random(System.currentTimeMillis()));
        this.elementsList = new ArrayList<>();
        if (elements != null) {
            for (T oneElement : elements) {
                this.elementsList.add(oneElement);
            }
        }
    }

    /**
     * Creates a new random choice supplier with given randomness.
     * @param rnd      randomness
     * @param elements elements to choose from
     */
    public RandomChoiceSupplier(final Random rnd, final T... elements) {
        this.random = (rnd != null) ? rnd : (new Random(System.currentTimeMillis()));
        this.elementsList = new ArrayList<>();
        if ((elements != null) && (elements.length > 0)) {
            for (int i = 0; i < elements.length; i++) {
                this.elementsList.add(elements[i]);
            }
        }
    }

    @Override
    public T get() {
        if (this.elementsList.isEmpty()) {
            return null;
        }
        // we don't cache the size because we know that the list is an ArrayList
        int index = this.random.nextInt(this.elementsList.size());
        return this.elementsList.get(index);
    }

}
