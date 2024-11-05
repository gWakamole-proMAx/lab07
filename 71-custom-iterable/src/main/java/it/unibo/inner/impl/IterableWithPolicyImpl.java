package it.unibo.inner.impl;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private final List<T> elements;
    private Predicate<T> filter;

    /**
     * Iterates over all the elements.
     * The default policy is to take all the elements.
     *
     * @param elements the elements to iterate over.
     */
    public IterableWithPolicyImpl(final T[] elements) {
        this(
            elements,
            new Predicate<>() {
                @Override
                public boolean test(T elem) {
                    return true;
                }
            }
        );
    }

    /**
     * Iterates over the elements that satisfy the given filter.
     * @param elements the elements to iterate over.
     * @param filter the filter to apply.
     */
    public IterableWithPolicyImpl(final T[] elements, final Predicate<T> filter) {
        this.elements = List.of(elements);
        this.filter = filter;
    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilterIterator();
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    private class FilterIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            while (currentIndex < elements.size()) {
                final T elem = elements.get(currentIndex);
                if (filter.test(elem)) {
                    return true;
                }
                currentIndex++;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return elements.get(currentIndex++);
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
