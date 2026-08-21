import java.util.Arrays;

class DynamicArray<T> {
    private Object[] array = new Object[2];
    private int size = 0;

    public void add(T value) {
        add(size, value);
    }

    public void add(int index, T value) {
        if (index < 0 || index > size) return;
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (index < 0 || index >= size) ? null : (T) array[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T value) {
        if (index < 0 || index >= size) return null;
        T old = (T) array[index];
        array[index] = value;
        return old;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) return null;
        T old = (T) array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null; // 最後一個無效格設為 null
        return old;
    }

    public int size() { return size; }
    public int capacity() { return array.length; }
}

public class DynamicArrayPractice {
    public static void main(String[] args) {
        DynamicArray<String> da = new DynamicArray<>();
        da.add("A"); 
        da.add("B"); 
        da.add("C"); // 觸發擴充為兩倍 (2 -> 4)
        
        System.out.println("Capacity: " + da.capacity() + " | Size: " + da.size());
        
        da.remove(1);
        System.out.println("移除 index 1 後，get(1): " + da.get(1)); // 應為 C
        System.out.println("測試 index -1: " + da.remove(-1));
        
        da.remove(0); 
        da.remove(0);
        System.out.println("空結構刪除: " + da.remove(0));
    }
}