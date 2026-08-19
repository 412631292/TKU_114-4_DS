import java.util.*;

public class CourseTagReport {
    public static void main(String[] args) {
        // 模擬輸入可能重複的課程標籤
        List<String> inputs = Arrays.asList("Java", "Web", "Java", "AI", "Database", "Web");
        
        // 1. List: 保存原始順序
        List<String> list = new ArrayList<>(inputs);
        
        // 2. Set: 保存不重複標籤 (使用 LinkedHashSet 保持順序)
        Set<String> set = new LinkedHashSet<>(list);
        
        // 3. Map: 統計次數
        Map<String, Integer> map = new HashMap<>();
        for (String tag : list) {
            map.put(tag, map.getOrDefault(tag, 0) + 1);
        }
        
        // 輸出與說明 (正確使用 %n 換行)
        System.out.println("=== 課程標籤統計 ===");
        System.out.printf("List (用途: 紀錄原始輸入，保留順序與重複): %s%n", list);
        System.out.printf("Set  (用途: 剔除重複標籤，適合做分類牆): %s%n", set);
        System.out.printf("Map  (用途: 統計各標籤出現的頻率次數): %s%n", map);
    }
}