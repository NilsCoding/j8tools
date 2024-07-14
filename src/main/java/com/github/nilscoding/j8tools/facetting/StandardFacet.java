package com.github.nilscoding.j8tools.facetting;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Standard Facet.
 * @param <T> type of facet value
 * @param <U> type of facet value
 * @author NilsCoding
 */
public class StandardFacet<T, U> implements IFacet<T, U> {

    /**
     * Map with facet values.
     */
    protected Map<Integer, FacetValue<U>> facetValues = new HashMap<>();
    /**
     * Facet name.
     */
    protected String name;
    /**
     * Value converter function.
     */
    protected Function<T, U> valueConverterFnc;
    /**
     * Hash function.
     */
    protected Function<U, Integer> hashFunction;

    /**
     * Creates a facet.
     */
    public StandardFacet() {
        this.hashFunction = new DefaultHashFunction<>();
        this.valueConverterFnc = (t) -> (U) t;
    }

    @Override
    public void setValueConverterFunction(Function<T, U> fnc) {
        this.valueConverterFnc = fnc;
        if (this.valueConverterFnc == null) {
            this.valueConverterFnc = (t) -> {
                return (U) t;
            };
        }
    }

    /**
     * Processes the given value for this facet.
     * @param value value to process
     */
    @Override
    public void processValue(T value) {
        U tmpValue = this.valueConverterFnc.apply(value);
        int hashcode = this.hashFunction.apply(tmpValue);
        FacetValue<U> fc = this.facetValues.get(hashcode);
        if (fc == null) {
            fc = new FacetValue(value, 1);
            this.facetValues.put(hashcode, fc);
        } else {
            fc.increaseCount();
        }
    }

    /**
     * Returns the name of this facet.
     * @return facet name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     * @param name name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a sorted list with all facet values.
     * @return sorted list with facet values
     */
    @Override
    public List<FacetValue<U>> getFacetValues() {
        return this.facetValues.values()
                .stream()
                .sorted(Comparator.comparing(FacetValue<U>::getCount).reversed())
                .collect(Collectors.toList());
    }

}
