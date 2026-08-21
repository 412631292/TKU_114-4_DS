import java.util.*;

public class CollectionChoiceReport {
    public static void main(String[] args) {
        System.out.println("=== 需求 1: 保留搜尋紀錄且允許重複 ===");
        System.out.println("介面: List | 實作: ArrayList");
        List<String> searchHistory = new ArrayList<>(Arrays.asList("Java", "Spring", "Java"));
        System.out.println("結果: " + searchHistory);

        System.out.println("\n=== 需求 2: 保存不重複會員編號 ===");
        System.out.println("介面: Set | 實作: HashSet");
        Set<String> memberIds = new HashSet<>(Arrays.asList("M01", "M02", "M01"));
        System.out.println("結果: " + memberIds);

        System.out.println("\n=== 需求 3: 以學號查詢成績 ===");
        System.out.println("介面: Map | 實作: HashMap");
        Map<String, Integer> grades = new HashMap<>();
        grades.put("S01", 95);
        grades.put("S02", 88);
        System.out.println("結果: S01 成績為 " + grades.get("S01"));

        System.out.println("\n=== 需求 4: 依到達順序處理列印工作 ===");
        System.out.println("介面: Queue | 實作: LinkedList");
        Queue<String> printJobs = new LinkedList<>(Arrays.asList("A.pdf", "B.pdf"));
        System.out.println("結果: 處理下一筆 -> " + printJobs.poll());

        System.out.println("\n=== 需求 5: 復原最近操作 ===");
        System.out.println("介面: Deque | 實作: ArrayDeque (作為 Stack)");
        Deque<String> undoStack = new ArrayDeque<>();
        undoStack.push("Action 1");
        undoStack.push("Action 2");
        System.out.println("結果: 復原操作 -> " + undoStack.pop());
    }
}