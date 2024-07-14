package com.github.nilscoding.j8tools.supplier;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Decorator which forwards each generated element to a Consumer.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class ForwardingSupplier<T> implements Supplier<T> {

    /**
     * Parent supplier.
     */
    protected Supplier<T> parent;
    /**
     * Consumer.
     */
    protected Consumer<? super T> consumer;

    /**
     * Creates a forwarding supplier.
     * @param parentSupplier     parent supplier
     * @param forwardingConsumer consumer to forward to
     */
    public ForwardingSupplier(final Supplier<T> parentSupplier, final Consumer<? super T> forwardingConsumer) {
        if (parentSupplier == null) {
            throw new IllegalArgumentException("Parent supplier cannot be null.");
        }
        this.parent = parentSupplier;
        this.consumer = forwardingConsumer;
    }

    @Override
    public T get() {
        T tmp = this.parent.get();
        if (this.consumer != null) {
            this.consumer.accept(tmp);
        }
        return tmp;
    }

}
