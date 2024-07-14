package com.github.nilscoding.j8tools.facetting;

import java.util.function.Function;

/**
 * Object accessor using object's function.
 * @param <T> type of object
 * @author NilsCoding
 */
public class MethodFieldAccessor<T> implements IObjectFieldAccessor<T>, IValueTransforming {

    /**
     * Field name.
     */
    protected String fieldName;
    /**
     * Accessor function.
     */
    protected Function<T, Object> accessFunction;
    /**
     * Value transforming function.
     */
    protected Function<Object, Object> valueTransformingFunction;

    /**
     * Creates a new Accessor instance with given field name and access function.
     * @param fieldName      field name
     * @param accessFunction access function
     */
    public MethodFieldAccessor(final String fieldName, final Function<T, Object> accessFunction) {
        this.fieldName = fieldName;
        this.accessFunction = accessFunction;
    }

    /**
     * Creates a new Accessor instance with given field name, access function and value-transforming function.
     * @param fieldName                 filed name
     * @param accessFunction            access function
     * @param valueTransformingFunction value-transforming function
     */
    public MethodFieldAccessor(final String fieldName,
                               final Function<T, Object> accessFunction,
                               final Function<Object, Object> valueTransformingFunction) {
        this.fieldName = fieldName;
        this.accessFunction = accessFunction;
        this.valueTransformingFunction = valueTransformingFunction;
    }

    @Override
    public Object getFieldValue(T obj) {
        if (obj == null) {
            return null;
        }
        if (this.accessFunction != null) {
            return this.accessFunction.apply(obj);
        }
        return null;
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }

    @Override
    public Function<Object, Object> getValueTransformingFunction() {
        return this.valueTransformingFunction;
    }

}
