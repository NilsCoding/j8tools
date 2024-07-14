package com.github.nilscoding.j8tools.supplier;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Supplier which creates objects of given types, optionally passing them to a post-creation conversion function.<br>
 * We could use a Consumer instead of the Function to apply field values but using a function allows wrapping the object
 * with same-type object.
 * @param <T> type of elements
 * @author NilsCoding
 */
public class ObjectCreationSupplier<T> implements Supplier<T> {

    /**
     * Class to create objects of.
     */
    protected Class<T> clazzToCreate;
    /**
     * Post-creation function, converting the created object.
     */
    protected Function<T, T> postCreationFunction;
    /**
     * Alternative supplier.
     */
    protected Supplier<T> alternativeSupplier = null;

    /**
     * Creates a new Supplier instance which creates objects of given type.
     * @param clazz class to create objects of
     */
    public ObjectCreationSupplier(final Class<T> clazz) {
        this(clazz, null);
    }

    /**
     * Creates a new Supplier instance which creates objects of given types and optionally converts them using the
     * given function (function must accept null parameter).
     * @param clazz                class to create objects of
     * @param postCreationFunction post creation function for object conversion,
     *                             must accept null (optional, can be null)
     */
    public ObjectCreationSupplier(final Class<T> clazz, final Function<T, T> postCreationFunction) {
        if (clazz != null) {
            // check for special cases
            if (clazz.isArray()) {
                this.clazzToCreate = null;
            } else if (clazz.equals(int.class) || clazz.equals(Integer.class)) {
                this.alternativeSupplier = () -> {
                    Integer i = 0;
                    return (T) i;
                };
            } else if (clazz.equals(long.class) || clazz.equals(Long.class)) {
                this.alternativeSupplier = () -> {
                    Long l = 0L;
                    return (T) l;
                };
            } else if (clazz.equals(double.class) || clazz.equals(Double.class)) {
                this.alternativeSupplier = () -> {
                    Double d = 0.0d;
                    return (T) d;
                };
            } else if (clazz.equals(float.class) || clazz.equals(Float.class)) {
                this.alternativeSupplier = () -> {
                    Float f = 0.0f;
                    return (T) f;
                };
            } else if (clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
                this.alternativeSupplier = () -> {
                    Boolean b = false;
                    return (T) b;
                };
            } else if (clazz.equals(byte.class) || clazz.equals(Byte.class)) {
                this.alternativeSupplier = () -> {
                    Byte b = (byte) 0;
                    return (T) b;
                };
            } else if (clazz.equals(char.class) || clazz.equals(Character.class)) {
                this.alternativeSupplier = () -> {
                    Character c = (char) 0;
                    return (T) c;
                };
            } else if (clazz.equals(short.class) || clazz.equals(Short.class)) {
                this.alternativeSupplier = () -> {
                    Short s = (short) 0;
                    return (T) s;
                };
            } else if (clazz.equals(String.class)) {
                this.alternativeSupplier = () -> (T) "";
            } else if (Map.class.isAssignableFrom(clazz)) {
                this.alternativeSupplier = () -> (T) new HashMap<>();
            } else if (List.class.isAssignableFrom(clazz)) {
                this.alternativeSupplier = () -> (T) new LinkedList<>();
            } else if (Set.class.isAssignableFrom(clazz)) {
                this.alternativeSupplier = () -> (T) new HashSet<>();
            }
        } else {
            this.clazzToCreate = null;
        }
        this.postCreationFunction = postCreationFunction;
    }

    /**
     * Creates a new object instance, either by using the Supplier or using the default constructor of class.
     * @return new object instance or null on error
     */
    protected T createInstance() {
        if (this.alternativeSupplier != null) {
            return this.alternativeSupplier.get();
        }
        try {
            return this.clazzToCreate.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public T get() {
        if (clazzToCreate == null) {
            return null;
        }
        T tmp = this.createInstance();
        if (this.postCreationFunction != null) {
            try {
                tmp = this.postCreationFunction.apply(tmp);
            } catch (Exception ex) {
            }
        }
        return tmp;
    }
}
