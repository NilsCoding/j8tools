package com.github.nilscoding.j8tools.facetting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define object field accessors.
 * @author NilsCoding
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UseFieldAccessors {

    /**
     * Accessor annotations.
     * @return Accessor annotations
     */
    UseFieldAccessor[] accessors();

}
