import java.util.Arrays;

class CircularQueue<T> {
    private Object[] array;
    private int front = 0;
    private int rear = 0;
    private int size = 0;

    public CircularQueue(int capacity) {
        array = new Object[capacity];
    }

    public void enqueue(T value) {
        if (size == array.length) return;
        array[rear] = value;
        rear = (rear + 1) % array.length;
        size++;
        printState();
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (size == 0) return null;
        T value = (T) array[front];
        array[front] = null; // 協助 GC 並方便觀察
        front = (front + 1) % array.length;
        size--;
        printState();
        return value;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    private void printState() {
        System.out.printf("Array: %s | Front: %d | Rear: %d | Size: %d%n", 
                Arrays.toString(array), front, rear, size);
    }
}

public class CircularQueuePractice {
    public static void main(String[] args) {
        CircularQueue<String> cq = new CircularQueue<>(4);
        
        System.out.println("--- 開始依序操作 ---");
        cq.enqueue("A"); cq.enqueue("B"); cq.enqueue("C");
        cq.dequeue(); cq.dequeue();
        cq.enqueue("D"); cq.enqueue("E"); cq.enqueue("F"); // 此時會觸發循環寫入
        cq.dequeue(); cq.enqueue("G");
        
        System.out.println("\n--- 依 FIFO 取出剩餘元素 ---");
        while (!cq.isEmpty()) {
            System.out.println("取出: " + cq.dequeue());
        }
    }
}