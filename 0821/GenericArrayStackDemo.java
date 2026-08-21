class ArrayStack<T> {
    private Object[] elements;
    private int top = -1;

    public ArrayStack(int capacity) {
        elements = new Object[capacity];
    }

    public void push(T value) {
        if (!isFull()) elements[++top] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) return null;
        T value = (T) elements[top];
        elements[top--] = null; // 協助 GC
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        return isEmpty() ? null : (T) elements[top];
    }

    public int size() { return top + 1; }
    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return top == elements.length - 1; }
}

public class GenericArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack<String> stringStack = new ArrayStack<>(2);
        stringStack.push("Java");
        stringStack.push("Python");
        stringStack.push("C++"); // 滿了，不應加入
        System.out.println("String pop: " + stringStack.pop());

        ArrayStack<Integer> intStack = new ArrayStack<>(3);
        intStack.push(100);
        System.out.println("Integer peek: " + intStack.peek());
        System.out.println("Integer size: " + intStack.size());
    }
}