package com.github.nilscoding.j8tools.facetting;

import java.util.List;
import java.util.function.Function;

/**
 * Interface for facet implementations.
 * @param <T> type of values
 * @param <U> type of values
 * @author NilsCoding
 */
public interface IFacet<T, U> {

    /**
     * Returns the facet name.
     * @return facet name
     */
    String getName();

    /**
     * Sets the facet name.
     * @param name facet name
     */
    void setName(String name);

    /**
     * Sets the value converter function.
     * @param fnc value converter function
     */
    void setValueConverterFunction(Function<T, U> fnc);

    /**
     * Processes the given value for this facet.
     * @param value value to process
     */
    void processValue(T value);

    /**
     * Returns a list with all facet values.
     * @return list with facet values
     */
    List<FacetValue<U>> getFacetValues();

}
