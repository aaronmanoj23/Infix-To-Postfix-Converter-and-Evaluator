import java.util.EmptyStackException;

public class ArrayStack<T> implements StackInterface<T> {
    private T[] stack;
    private int topIndex;
    private static final int DEFAULT_CAPACITY = 50;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int initialCapacity) {
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
    }

    public void push(T newEntry) {
        if (topIndex >= stack.length - 1) {
            expandArray();
        }
        stack[++topIndex] = newEntry;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }
    }

    public T top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return stack[topIndex];
        }
    }

    public boolean isEmpty() {
        return topIndex < 0;
    }

    public void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

    private void expandArray() {
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[2 * stack.length];
        System.arraycopy(stack, 0, newArray, 0, stack.length);
        stack = newArray;
    }
}

