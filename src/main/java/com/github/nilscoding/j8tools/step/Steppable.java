package com.github.nilscoding.j8tools.step;

import java.util.function.Supplier;

/**
 * Interface for objects that can step forward and backwards.
 * Step interval depends on object type. Same object type can have different step intervals, but this MUST NOT be
 * handled in same object holder.
 * Steppable objects are stateful, so applying the stepping their internal value will change.
 * @param <T>    type of element
 * @param <U>    type of stepping unit class
 * @param <A>    type of stepping amount
 * @param <S>    type of stepping
 * @param <SELF> type of self
 * @author NilsCoding
 */
public interface Steppable<T,
        U extends Class<?>,
        A,
        S extends Step<U, A, T, S>,
        SELF extends Steppable<T, U, A, S, SELF>>
        extends Comparable<Steppable<T, U, A, S, SELF>> {

    /**
     * Returns the current value. Implementations SHOULD return an effective-final instance or a copy.
     * @return current value
     */
    T getValue();

    /**
     * Returns the step size. Implementations SHOULD return an effective-final instance or a copy.
     * @return step size
     */
    S getStepSize();

    /**
     * Step forward.
     * @return current instance
     */
    SELF forward();

    /**
     * Step backward.
     * @return current instance
     */
    SELF backward();

    /**
     * Returns an inverse instance, stepping in the opposite direction.
     * @return inverse instance
     */
    SELF inverse();

    /**
     * Returns a new instance with the initial value being the same as this instance started.
     * @return instance started with same value as this instance started with
     */
    SELF fromStart();

    /**
     * Returns this Steppable as a Supplier. Will start at the current value.
     * @return supplier
     */
    Supplier<T> asSupplier();

}
