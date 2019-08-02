package com.plietnov.task1;

import com.plietnov.task1.entity.Product;

import java.util.*;
import java.util.function.Predicate;

public class Container<T extends Product> implements List<T> {

    private static final int INITIAL_CAPACITY = 10;

    private T[] array;

    private int size;

    public Container() {
        array = (T[]) new Product[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ContainerIterator(t -> true);
    }

    public Iterator<T> iterator(Predicate<T> predicate) {
        return new ContainerIterator(predicate);
    }

    private class ContainerIterator implements Iterator<T> {
        private int cursor;

        private T[] arrayItr;

        private ContainerIterator(Predicate<T> predicate) {
            arrayItr = (T[]) Arrays.stream(toArray(array))
                    .filter(predicate)
                    .toArray(Product[]::new);
        }

        @Override
        public boolean hasNext() {
            return cursor != arrayItr.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return arrayItr[cursor++];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            a = (T[]) Arrays.copyOf(array, size, a.getClass());
        } else {
            System.arraycopy(array, 0, a, 0, size);
            if (a.length > size) {
                a[size] = null;
            }
        }
        return a;
    }

    @Override
    public boolean add(T element) {
        if (size == array.length) {
            increaseCapacity();
        }
        array[size++] = element;
        return true;
    }

    private void increaseCapacity() {
        array = Arrays.copyOf(array, array.length * INITIAL_CAPACITY);
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean flag = true;
        for (Object t : c.toArray()) {
            flag &= contains(t);
        }
        return flag;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        for (T t : c) {
            add(t);
        }
        return true;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T tmp = array[index];
        array[index] = element;
        return tmp;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T tmp = array[index];
        fastRemove(index);
        return tmp;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndexAdd(index);
        if (c == null || c.isEmpty()) {
            return false;
        }
        boolean state = false;
        for (T t : c) {
            add(index++, t);
            state = true;
        }
        return state;
    }

    @Override
    public void add(int index, T element) {
        checkIndexAdd(index);
        if (size == array.length) {
            increaseCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * @checkIndexAdd check validations input index before add date
     */
    private void checkIndexAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }
    }

    /**
     * @checkIndex check validations input index before get or set
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean state = true;
        for (Object t : c.toArray()) {
            if (contains(t)) {
                state &= remove(t);
            }
        }
        return state;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean state = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(array[i])) {
                remove(array[i--]);
            }
        }
        return state;
    }

    @Override
    public void clear() {
        array = (T[]) new Product[INITIAL_CAPACITY];
        size = 0;
    }

    private void fastRemove(int index) {
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
