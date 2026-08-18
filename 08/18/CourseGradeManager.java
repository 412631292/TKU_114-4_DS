class CourseGrade {
    private String studentId;
    private String name;
    private double regularScore;
    private double midtermScore;
    private double finalScore;
    private double attendanceScore;

    public CourseGrade(String studentId, String name, double regular, double midterm, double finalS, double attendance) {
        this.studentId = studentId;
        this.name = name;
        this.regularScore = clampScore(regular);
        this.midtermScore = clampScore(midterm);
        this.finalScore = clampScore(finalS);
        this.attendanceScore = clampScore(attendance);
    }

    // 限制分數在 0-100 之間
    private double clampScore(double score) {
        return Math.max(0, Math.min(100, score));
    }

    public double calculateFinalScore() {
        return (regularScore * 0.5) + (midtermScore * 0.2) + (finalScore * 0.2) + (attendanceScore * 0.1);
    }

    public String getLevel() {
        double total = calculateFinalScore();
        if (total >= 90) return "A";
        if (total >= 80) return "B";
        if (total >= 70) return "C";
        if (total >= 60) return "D";
        return "F";
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("%s (%s) - 總分: %.2f 等第: %s", studentId, name, calculateFinalScore(), getLevel());
    }
}

public class CourseGradeManager {
    public static void main(String[] args) {
        System.out.println("=== 課程成績物件系統 ===");
        
        CourseGrade[] grades = {
            new CourseGrade("S01", "張三", 85, 90, 88, 100),
            new CourseGrade("S02", "李四", 50, 45, 60, 80),
            new CourseGrade("S03", "王五", 95, 92, 96, 100),
            new CourseGrade("S04", "趙六", 70, 65, 75, 90),
            new CourseGrade("S05", "陳七", 40, 30, 50, 60)
        };

        double sum = 0;
        CourseGrade highest = grades[0];

        System.out.println("--- 成績清單 ---");
        for (CourseGrade grade : grades) {
            System.out.println(grade);
            double finalScore = grade.calculateFinalScore();
            sum += finalScore;
            
            if (finalScore > highest.calculateFinalScore()) {
                highest = grade;
            }
        }

        System.out.printf("\n全班平均: %.2f\n", sum / grades.length);
        System.out.println("最高分: " + highest.getName() + " (" + highest.calculateFinalScore() + ")");
        
        System.out.println("\n--- 不及格名單 ---");
        for (CourseGrade grade : grades) {
            if ("F".equals(grade.getLevel())) {
                System.out.println(grade.getName());
            }
        }
    }
}