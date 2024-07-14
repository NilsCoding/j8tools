package com.github.nilscoding.j8tools.supplier.mutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Mutator class, creating mutations of given elements, combining them into "chained" values.<br>
 * Please note that this class is not thread-safe.
 * @param <T> type of source elements
 * @param <I> type of intermediate mutation elements
 * @param <R> type of provided elements
 * @author NilsCoding
 */
public class Mutator<T, I, R> {

    /**
     * List with source elements.
     */
    protected List<T> srcElements;
    /**
     * Combination function.
     */
    protected BiConsumer<I, T> combFnc;
    /**
     * Temporary element creator.
     */
    protected Supplier<I> tempCreator;
    /**
     * Result converter.
     */
    protected Function<I, R> resultConverter;
    /**
     * Indexes.
     */
    protected List<Integer> indexes = new ArrayList<>(Arrays.asList(-1));

    /**
     * Creates a new Mutator instance.
     * @param sourceElements list with source elements
     * @param collector      collector
     */
    public Mutator(final List<T> sourceElements, final Collector<T, I, R> collector) {
        if (sourceElements != null) {
            this.srcElements = new ArrayList<>(sourceElements);
        } else {
            this.srcElements = new ArrayList<>(1);
        }
        if (collector != null) {
            this.combFnc = collector.accumulator();
            if (this.combFnc == null) {
                this.combFnc = (t, u) -> {
                };
            }
            this.tempCreator = collector.supplier();
            this.resultConverter = collector.finisher();
        }
    }

    /**
     * Creates the next element.
     * @return next element, might be null
     */
    public R createNextElement() {
        if (this.srcElements == null
                || this.srcElements.isEmpty()
                || this.tempCreator == null
                || this.resultConverter == null) {
            return null;
        }
        // first, increase the index
        int maxIdxValue = this.srcElements.size();
        int idxPos = 0;
        while (true) {
            int prevIdxValue = this.indexes.get(idxPos);
            int nextIdxValue = prevIdxValue + 1;
            if (nextIdxValue < maxIdxValue) {
                this.indexes.set(idxPos, nextIdxValue);
                break;
            }
            this.indexes.set(idxPos, 0);
            if (this.indexes.size() - 1 == idxPos) {
                this.indexes.add(0);
                break;
            }
            idxPos++;
        }

        // then create result
        I tmpObj = this.tempCreator.get();
        // get indexes in reverse order, fetch element and feed it to combining function
        for (int i = this.indexes.size() - 1; i >= 0; i--) {
            int idx = this.indexes.get(i);
            T indexElem = this.srcElements.get(idx);
            this.combFnc.accept(tmpObj, indexElem);
        }
        R resObj = this.resultConverter.apply(tmpObj);
        return resObj;
    }

}
