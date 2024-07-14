package com.github.nilscoding.j8tools.facetting;

/**
 * Interface for object field accessors.
 * @param <T> type of object
 * @author NilsCoding
 */
public interface IObjectFieldAccessor<T> {

    /**
     * Returns the field value.
     * @param obj object to get field value from
     * @return field value
     */
    Object getFieldValue(T obj);

    /**
     * Returns the field name.
     * @return field name
     */
    String getFieldName();

}
