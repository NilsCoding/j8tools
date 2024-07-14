package com.github.nilscoding.j8tools.facetting;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Facet-building Consumer.
 * @param <T> type of object
 * @author NilsCoding
 */
public class FacettingConsumer<T> implements Consumer<T> {

    /**
     * List with field accessors.
     */
    protected final List<IObjectFieldAccessor<T>> acc = new LinkedList<>();
    /**
     * Facetting.
     */
    protected Facets facetting;
    /**
     * Map with field accessors by class.
     */
    protected Map<String, List<IObjectFieldAccessor<T>>> accForClass = new HashMap<>();

    /**
     * Creates a facet-building consumer using Annotation-based accessor detection.
     * @param facetCreator facet creator
     */
    public FacettingConsumer(final Supplier<IFacet> facetCreator) {
        this.facetting = new Facets(facetCreator);
    }

    /**
     * Creates a facet-building consumer with given object field accessors.
     * @param facetCreator facet creator
     * @param accessors    object field accessors
     */
    public FacettingConsumer(final Supplier<IFacet> facetCreator, final Collection<IObjectFieldAccessor<T>> accessors) {
        this.facetting = new Facets(facetCreator);
        if ((accessors != null) && (accessors.isEmpty() == false)) {
            for (IObjectFieldAccessor oneAcc : accessors) {
                if (oneAcc instanceof AnnotationBasedAccessor) {
                    // one annotation-based accessor overrides all other defined values
                    this.acc.clear();
                    this.acc.add(oneAcc);
                    break;
                }
                if (oneAcc != null) {
                    this.acc.add(oneAcc);
                }
            }
        }
    }

    /**
     * Creates a facet-building consumer with given object field accessors.
     * @param facetCreator facet creator
     * @param accessors    object field accessors
     */
    public FacettingConsumer(final Supplier<IFacet> facetCreator, final IObjectFieldAccessor... accessors) {
        this.facetting = new Facets(facetCreator);
        if ((accessors != null) && (accessors.length > 0)) {
            for (IObjectFieldAccessor oneAcc : accessors) {
                if (oneAcc instanceof AnnotationBasedAccessor) {
                    // one annotation-based accessor overrides all other defined values
                    this.acc.clear();
                    this.acc.add(oneAcc);
                    break;
                }
                if (oneAcc != null) {
                    this.acc.add(oneAcc);
                }
            }
        }
    }

    @Override
    public void accept(T obj) {
        if (obj == null) {
            return;
        }

        Collection<IObjectFieldAccessor<T>> accessors = this.acc;
        if (
                ((accessors == null) || (accessors.isEmpty()))
                        || ((this.acc.size() == 1) && (this.acc.get(0) instanceof AnnotationBasedAccessor))
        ) {
            List<IObjectFieldAccessor<T>> accListForClass = this.accForClass.get(obj.getClass().getName());
            if (accListForClass == null) {
                accListForClass = new LinkedList<>();
                UseFieldAccessors ufasAnn = obj.getClass().getAnnotation(UseFieldAccessors.class);
                UseFieldAccessor[] ufas = null;
                if (ufasAnn != null) {
                    ufas = ufasAnn.accessors();
                } else {
                    ufas = new UseFieldAccessor[]{obj.getClass().getAnnotation(UseFieldAccessor.class)};
                }
                for (UseFieldAccessor oneUfa : ufas) {
                    if (oneUfa == null) {
                        continue;
                    }
                    try {
                        Class<? extends IObjectFieldAccessor> clazz = oneUfa.accessor();
                        String fieldName = oneUfa.fieldName();
                        if ((fieldName != null) && (fieldName.isEmpty() == false) && (clazz != null)) {
                            // find constructor with string parameter
                            Constructor<? extends IObjectFieldAccessor> ctr = clazz.getConstructor(String.class);
                            IObjectFieldAccessor oneAccessor = ctr.newInstance(fieldName);
                            accListForClass.add(oneAccessor);
                        }
                    } catch (Exception ex) {
                    }
                }
                this.accForClass.put(obj.getClass().getName(), accListForClass);
            }
            accessors = accListForClass;
        }

        for (IObjectFieldAccessor oneAcc : accessors) {
            String fieldName = oneAcc.getFieldName();
            Object fieldValue = oneAcc.getFieldValue(obj);
            if (oneAcc instanceof IValueTransforming) {
                Function<Object, Object> fnc = ((IValueTransforming) oneAcc).getValueTransformingFunction();
                if (fnc != null) {
                    fieldValue = fnc.apply(fieldValue);
                }
            }
            this.facetting.processValue(fieldName, fieldValue);
        }
    }

    /**
     * Returns the Facets instance.
     * @return Facets instance
     */
    public Facets getFacets() {
        return this.facetting;
    }

}
