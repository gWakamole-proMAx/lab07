package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private T[] array;

    public IterableWithPolicyImpl (final T[] arrayConst) {
        this.array = Arrays.copyOf(arrayConst, arrayConst.length);
    }   

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterableWithPolicyImpl(array);
    }

    /**
     * InnerIterableWithPolicyImpl
     */
    public class InnerIterableWithPolicyImpl implements Iterator<T> {
        private int current;

        InnerIterableWithPolicyImpl (final T[] array) {
            IterableWithPolicyImpl.this.array = array;
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < IterableWithPolicyImpl.this.array.length;
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
