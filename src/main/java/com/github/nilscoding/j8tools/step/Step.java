package com.github.nilscoding.j8tools.step;

/**
 * Represents a stepping, with unit and amount.
 * @param <U>    type of unit
 * @param <A>    type of amount
 * @param <T>    type of elements to apply addition or subtraction to
 * @param <SELF> type of self
 * @author NilsCoding
 */
public interface Step<U extends Class<?>, A, T, SELF extends Step<U, A, T, SELF>> {

    /**
     * Returns the unit type.
     * @return unit type
     */
    U getUnit();

    /**
     * Returns the amount.
     * @return amount
     */
    A getAmount();

    /**
     * Adds current amount to the given element, returning a new element.
     * @param obj element to add amount to
     * @return result
     * @throws NullPointerException thrown if given parameter is null
     */
    T add(T obj) throws NullPointerException;

    /**
     * Subtracts the current amount from the given element, returning a new element.
     * @param obj element to subtract from
     * @return result
     * @throws NullPointerException thrown if given parameter is null
     */
    T subtract(T obj) throws NullPointerException;

    /**
     * Returns an inverted step to this step.
     * @return inverted step
     */
    SELF inverse();

    /**
     * Returns a copy of this step instance.
     * @return copied instance
     */
    SELF copy();

}
