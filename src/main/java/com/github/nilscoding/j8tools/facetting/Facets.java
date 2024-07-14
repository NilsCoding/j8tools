package com.github.nilscoding.j8tools.facetting;

import java.util.*;
import java.util.function.Supplier;

/**
 * Facets.
 * @author NilsCoding
 */
public class Facets {

    /**
     * Map with facets.
     */
    protected final Map<String, IFacet<?, ?>> facets = new LinkedHashMap<>();
    /**
     * Supplier for creating facets.
     */
    protected Supplier<IFacet> facetCreator;

    /**
     * Creates a new facet instance.
     * @param facetCreator facet creator
     */
    public Facets(final Supplier<IFacet> facetCreator) {
        this.facetCreator = facetCreator;
    }

    /**
     * Processes the given value with given name.
     * @param name  name
     * @param value value
     */
    public void processValue(String name, Object value) {
        if (name == null) {
            return;
        }
        IFacet f = this.facets.get(name);
        if (f == null) {
            f = this.facetCreator.get();
            f.setName(name);
            this.facets.put(name, f);
        }
        f.processValue(value);
    }

    /**
     * Returns all facets.
     * @return facets
     */
    public List<IFacet> getAllFacets() {
        return new LinkedList<>(this.facets.values());
    }

    /**
     * Returns a facet by name.
     * @param name name
     * @return Facet, can be null
     */
    public IFacet getFacetByName(String name) {
        return this.facets.get(name);
    }

    /**
     * Returns all facet names.
     * @return all facet names
     */
    public Set<String> getFacetNames() {
        return new LinkedHashSet<>(this.facets.keySet());
    }

}
