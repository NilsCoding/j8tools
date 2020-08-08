package com.github.nilscoding.j8tools.collection;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Iterator utils
 * @author NilsCoding
 */
public class Iterators {
    
    private Iterators() {
    }
    
    /**
     * Returns an Stream backed by an Iterator
     * @param <T>   type of elements
     * @param itr   iterator
     * @return  stream
     */
    public static <T> Stream<T> toStream(Iterator<T> itr) {
        if (itr == null) {
            return Stream.empty();
        }
        Spliterator<T> spliterator = Spliterators.spliterator(itr, Long.MAX_VALUE, 0);
        return StreamSupport.stream(spliterator, false);
    }
    
}
