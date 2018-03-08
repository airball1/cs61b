public class LinkedListDeque<T> {
    private class LinkedListNode {
        public T item;
        public LinkedListNode next;
        public LinkedListNode prev;

        public LinkedListNode() {
            item = null;
            next = null;
            prev = null;
        }

        public LinkedListNode(T item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }

    /** The first item (if it exists) is at sentinel.next.
     * The last item (if it exists) is at sentinel.prev. */

    private LinkedListNode sentinel;
    private int size;

    /** creates an empty List. */
    public LinkedListDeque() {
        sentinel = new LinkedListNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }


    /** Adds item to the front of the list. */
    public void addFirst(T item) {
        LinkedListNode first = new LinkedListNode(item);
        first.next = sentinel.next;
        first.prev = sentinel;
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    /** Adds item to the end of the list. */
    public void addLast(T item) {
        LinkedListNode last = new LinkedListNode(item);
        last.prev = sentinel.prev;
        last.next = sentinel;
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
       return size == 0;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        LinkedListNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return item;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.prev.next = sentinel;
        size -= 1;
        return item;
    }

    /** Gets the item at the given index,  If no such item exists, returns null */
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        LinkedListNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /** The helper method for getRecursive(int index) */
    private T getRecursive(int index, LinkedListNode p) {
        if (index == 0) {
            return p.item;
        } else {
            return getRecursive(index - 1, p.next);
        }
    }

    /** Gets the item at the given index, using recursion  */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }

}