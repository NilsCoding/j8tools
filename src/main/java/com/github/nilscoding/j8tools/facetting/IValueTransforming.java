package com.github.nilscoding.j8tools.facetting;

import java.util.function.Function;

/**
 * Interface to provide value transforming.
 * @author NilsCoding
 */
public interface IValueTransforming {

    /**
     * Returns the value-transforming function.
     * @return value-transforming function
     */
    Function<Object, Object> getValueTransformingFunction();

}
