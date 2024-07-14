package com.github.nilscoding.j8tools.facetting;

import java.util.Map;
import java.util.function.Function;

/**
 * Object accessor using Map getter.
 * @param <T> type of object
 * @author NilsCoding
 */
public class MapAccessor<T extends Map> implements IObjectFieldAccessor<T>, IValueTransforming {

    /**
     * Field name.
     */
    protected String fieldName;
    /**
     * Value-transforming function.
     */
    protected Function<Object, Object> valueTransformingFunction;

    /**
     * Creates a new Map accessor with given field name.
     * @param fieldName field name
     */
    public MapAccessor(final String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Creates a new Map accessor with given field name and value-transforming function.
     * @param fieldName                 field name
     * @param valueTransformingFunction value-transforming function
     */
    public MapAccessor(final String fieldName, final Function<Object, Object> valueTransformingFunction) {
        this.fieldName = fieldName;
        this.valueTransformingFunction = valueTransformingFunction;
    }

    @Override
    public Object getFieldValue(T obj) {
        if ((obj == null) || (this.fieldName == null)) {
            return null;
        }
        if ((obj instanceof Map) == false) {
            return null;
        }
        Map m = (Map) obj;
        return m.get(this.fieldName);
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
