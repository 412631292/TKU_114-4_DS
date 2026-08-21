import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {
    public static void processList(List<Integer> list) {
        // 尾端新增
        list.add(10);
        list.add(20);
        // 指定位置插入
        list.add(1, 15); 
        // 搜尋
        boolean hasTwenty = list.contains(20);
        // 刪除
        list.remove(Integer.valueOf(10));
        // 總和
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        
        System.out.printf("型態: %-10s | 內容: %s | 搜尋20: %b | 總和: %d%n", 
                list.getClass().getSimpleName(), list, hasTwenty, sum);
    }

    public static void main(String[] args) {
        processList(new ArrayList<>());
        processList(new LinkedList<>());
        
        /*
         * 內部成本差異說明：
         * 1. 尾端新增：ArrayList 攤平時間為 O(1) (容量滿時需擴容搬移)；LinkedList 始終為 O(1)。
         * 2. 插入與刪除：ArrayList 需搬移後續所有元素，成本為 O(n)；LinkedList 只要改變指標，但尋找位置仍需 O(n)。
         * 3. 搜尋與存取：ArrayList 支援 O(1) 隨機存取；LinkedList 必須走訪節點，存取指定位置為 O(n)。
         */
    }
}