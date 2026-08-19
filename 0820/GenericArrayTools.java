import java.util.Arrays;

public class GenericArrayTools {
    
    // 1. 計算目標出現次數
    public static <T> int countMatches(T[] data, T target) {
        if (data == null) return 0;
        int count = 0;
        for (T item : data) {
            if (item != null && item.equals(target)) count++;
        }
        return count;
    }

    // 2. 取得陣列最後一個元素
    public static <T> T last(T[] data) {
        return (data == null || data.length == 0) ? null : data[data.length - 1];
    }

    // 3. 交換陣列中兩個位置的元素
    public static <T> void swap(T[] data, int first, int second) {
        if (data == null || first < 0 || second < 0 || 
            first >= data.length || second >= data.length) return;
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    // 補上程式進入點 main 方法
    public static void main(String[] args) {
        System.out.println("=== 泛型陣列工具測試 ===\n");
        
        String[] words = {"Java", "Python", "Java", "C++"};
        Integer[] numbers = {10, 20, 30};
        
        // 測試一：正常操作
        System.out.println("【正常操作測試】");
        System.out.println("'Java' 出現次數: " + countMatches(words, "Java") + " (預期: 2)");
        System.out.println("numbers 最後一個元素: " + last(numbers) + " (預期: 30)");
        
        System.out.println("交換前 words: " + Arrays.toString(words));
        swap(words, 0, 1);
        System.out.println("交換後 words: " + Arrays.toString(words) + " (Java 與 Python 交換)");
        
        // 測試二：邊界條件防呆
        System.out.println("\n【邊界條件防呆測試】");
        System.out.println("傳入 null 陣列給 last: " + last((String[]) null) + " (預期: null)");
        System.out.println("傳入空陣列給 last: " + last(new Integer[0]) + " (預期: null)");
        
        System.out.print("嘗試越界交換 swap(numbers, -1, 5)... ");
        swap(numbers, -1, 5); // 不應發生 Exception
        System.out.println("成功防禦！沒有發生崩潰。");
    }
}