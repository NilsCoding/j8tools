package com.github.nilscoding.j8tools.consumer;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Consumer which collects the consumed elements.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class CollectingConsumer<T> implements Consumer<T> {

    /**
     * Supplier for the collection.
     */
    protected Supplier<Collection<T>> collectionSupplier;
    /**
     * Collection.
     */
    protected Collection<T> collection;
    /**
     * Flag if collection has been supplied yet.
     */
    protected boolean collectionSupplied = false;

    /**
     * Creates a new consumer with LinkedList collection.
     */
    public CollectingConsumer() {
        this(LinkedList::new);
    }

    /**
     * Creates a new consumer with given collection supplier.
     * @param collectionSupplier collection supplier
     */
    public CollectingConsumer(final Supplier<Collection<T>> collectionSupplier) {
        this.collectionSupplier = collectionSupplier;
        if (this.collectionSupplier == null) {
            this.collectionSupplier = LinkedList::new;
        }
    }

    /**
     * Creates a new consumer with given collection.
     * @param collection collection
     */
    public CollectingConsumer(final Collection<T> collection) {
        this.collection = collection;
        this.collectionSupplied = true;
    }

    @Override
    public void accept(T t) {
        if (this.collectionSupplied == false) {
            this.collectionSupplied = true;
            this.collection = this.collectionSupplier.get();
        }
        if (this.collection != null) {
            this.collection.add(t);
        }
    }

    /**
     * Returns the collection.
     * @return collection
     */
    public Collection<T> getCollection() {
        return collection;
    }

}
