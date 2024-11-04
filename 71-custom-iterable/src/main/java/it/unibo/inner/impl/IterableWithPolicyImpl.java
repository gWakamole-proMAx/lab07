package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private T[] array;
    private Predicate<T> filter;

    public IterableWithPolicyImpl (final T[] arrayConst) {
        this(arrayConst, new Predicate<T>() {
            @Override
            public boolean test(T elem) {
                return true;
            }    
        });
    }  

    
    public IterableWithPolicyImpl (final T[] elements, final Predicate<T> filter) {
        this.array = Arrays.copyOf(elements, elements.length);
        setIterationPolicy(filter);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterableWithPolicyImpl();
    }

    /**
     * InnerIterableWithPolicyImpl
     */
    public class InnerIterableWithPolicyImpl implements Iterator<T> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            while (this.current < IterableWithPolicyImpl.this.array.length && !IterableWithPolicyImpl.this.filter.test(IterableWithPolicyImpl.this.array[current])) {
                this.current++;
            }

            return this.current < IterableWithPolicyImpl.this.array.length;
        }

        @Override
        public T next() {
            if(this.hasNext()){
                return IterableWithPolicyImpl.this.array[this.current++];
            }
            throw new java.util.NoSuchElementException();
        }

        
    }
}
