package com.github.nilscoding.j8tools.facetting;

import java.util.function.Function;

/**
 * Default hash code function (using hashCode() on object).
 * @param <U> type of objects
 * @author NilsCoding
 */
public class DefaultHashFunction<U> implements Function<U, Integer> {

    @Override
    public Integer apply(U u) {
        if (u == null) {
            return 0;
        }
        return u.hashCode();
    }

}
