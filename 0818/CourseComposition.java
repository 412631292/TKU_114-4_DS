// 定義 Instructor (講師) 類別
class Instructor {
    private String id;
    private String name;

    // Constructor
    public Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // 提供 public 方法讓外部取得講師資訊
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

// 定義 Course (課程) 類別
class Course {
    private String courseCode;
    private String title;
    
    // 透過 Composition (組合) 保存 Instructor 的 Reference (參考)
    // 這裡不另外宣告 instructorName，避免資料重複與不一致
    private Instructor instructor;

    // Constructor
    public Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
    }

    // summary()：回傳完整課程資訊
    public String summary() {
        // 透過 instructor 物件的 reference 動態取得教師名稱與 ID
        return String.format("課程代碼: %-6s | 課程名稱: %-15s | 授課教師: %s (ID: %s)", 
                courseCode, title, instructor.getName(), instructor.getId());
    }
}

// 主程式類別
public class CourseComposition {
    public static void main(String[] args) {
        System.out.println("=== 課程與講師組合系統測試開始 ===\n");

        // 1. 建立一個 Instructor 物件
        Instructor sharedInstructor = new Instructor("T-9901", "李教授");
        System.out.println("成功建立講師檔案：" + sharedInstructor.getName() + "\n");

        // 2. 建立至少兩門課，並將同一個 instructor 傳入（共用同一個 reference）
        Course course1 = new Course("CS-101", "資料結構", sharedInstructor);
        Course course2 = new Course("CS-102", "演算法", sharedInstructor);

        // 3. 輸出課程摘要
        System.out.println("--- 課程清單 ---");
        System.out.println(course1.summary());
        System.out.println(course2.summary());

        System.out.println("\n=== 課程與講師組合系統測試結束 ===");
    }
}