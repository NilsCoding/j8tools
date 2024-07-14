package com.github.nilscoding.j8tools.facetting;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Object accessor using getter for field via reflection.
 * @param <T> type of object
 * @author NilsCoding
 */
public class ReflectionGetterFieldAccessor<T> implements IFieldNameBasedAccessor<T> {

    /**
     * Field name.
     */
    protected String fieldName;
    /**
     * Read method.
     */
    protected Method readMethod;
    /**
     * Flag indicating if read method is resolved.
     */
    protected boolean readMethodResolved = false;

    /**
     * Creates a new Accessor instance with given field name.
     * @param fieldName field name
     */
    public ReflectionGetterFieldAccessor(final String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public Object getFieldValue(T obj) {
        if (obj == null) {
            return null;
        }
        if (this.readMethodResolved == false) {
            this.readMethodResolved = true;
            try {
                PropertyDescriptor pd = new PropertyDescriptor(this.fieldName, obj.getClass());
                this.readMethod = pd.getReadMethod();
            } catch (Exception ex) {
            }
        }
        if ((this.readMethodResolved == false) || (this.readMethod == null)) {
            return null;
        }
        try {
            return this.readMethod.invoke(obj, (Object[]) null);
        } catch (Exception ex) {
        }
        return null;
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }

}
