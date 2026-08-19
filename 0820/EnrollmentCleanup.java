import java.util.*;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList(
            "Alice", "", null, "Bob", "Alice", "   ", "Charlie", "Bob"
        ));

        System.out.println("清理前: " + names);

        // 使用 Iterator 清理不合法資料
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            String name = it.next();
            if (name == null || name.trim().isEmpty()) {
                it.remove();
            }
        }

        // 使用 Set 找出重複姓名
        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (String name : names) {
            if (!seen.add(name)) {
                duplicates.add(name);
            }
        }

        System.out.println("清理後: " + names);
        System.out.println("重複報告: " + duplicates);
    }
}