package com.github.nilscoding.j8tools.facetting;

/**
 * "Marker" implementation to let the consumer use annotations only.
 * @param <T> type of object
 * @author NilsCoding
 */
public final class AnnotationBasedAccessor<T> implements IObjectFieldAccessor<T> {

    /**
     * Creates a new accessor instance.
     */
    public AnnotationBasedAccessor() {
    }

    @Override
    public Object getFieldValue(T obj) {
        return null;
    }

    @Override
    public String getFieldName() {
        return null;
    }

}
