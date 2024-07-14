package com.github.nilscoding.j8tools.facetting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define method field accessor.
 * @author NilsCoding
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface UseFieldAccessor {

    /**
     * Field name.
     * @return field name
     */
    String fieldName();

    /**
     * Accessor class.
     * @return Accessor class
     */
    Class<? extends IFieldNameBasedAccessor> accessor();

}
