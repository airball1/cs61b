public class ArrayDeque<T> {
    private T items[];
    private int size;
    private int nextFirst; // The next item we want to add, will go into the position nextFirst.
    private int nextLast; // The last item we want to add, will go into position nextLast.
    private int capacity; // the capacity of the deque.

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        capacity = 8;
    }

    public void resize(int c) {
        T[] newitems = (T []) new Object[c];
        for (int i = 0; i < size; i += 1) {
            newitems[i] = items[(nextFirst + 1 + i) % capacity];
        }
        nextFirst = c - 1;
        nextLast = size;
        capacity = c;
        items = newitems;
    }

    public void addFirst(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }

        items[nextFirst] = item;
        size += 1;
        nextFirst = (nextFirst + capacity - 1) % capacity;
    }

    public void addLast(T item) {
        if (size == capacity) {
            resize(capacity * 2)；
        }

        items[nextLast] = item;
        size += 1;
        nextLast = (nextLast + 1) % capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            System.out.print(items[(nextFirst + 1 + i) % capacity] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (capacity >= 16 && capacity / 4 > size) {
            resize(capacity / 2);
        }

        nextFirst = (nextFirst + 1) % capacity;
        size -= 1;
        T removed = items[nextFirst];
        items[nextFirst] = null;
        return removed;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (capacity >= 16 && capacity / 4 > size) {
            resize(capacity / 2);
        }

        nextLast = (nextLast + capacity - 1) % capacity;
        size -= 1;
        T removed = items[nextLast];
        items[nextLast] = null;
        return removed;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % capacity];
    }
}
