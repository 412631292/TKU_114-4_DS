import java.util.*;

class CourseData {
    String studentId; String tag; int score;
    public CourseData(String studentId, String tag, int score) {
        this.studentId = studentId; this.tag = tag; this.score = score;
    }
    public String getGrade() {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
    @Override public String toString() { return String.format("[%s|%s|%d]", studentId, tag, score); }
}

public class CourseCollectionManager {
    private List<CourseData> records = new ArrayList<>();

    public void add(CourseData data) { records.add(data); }

    public void updateScore(String studentId, int newScore) {
        for (CourseData d : records) {
            if (d.studentId.equals(studentId)) d.score = newScore;
        }
    }

    public List<CourseData> findByTag(String tag) {
        List<CourseData> result = new ArrayList<>();
        for (CourseData d : records) if (d.tag.equals(tag)) result.add(d);
        return result;
    }

    public Map<String, Integer> scoreDistribution() {
        Map<String, Integer> dist = new HashMap<>();
        dist.put("A", 0); dist.put("B", 0); dist.put("C", 0); dist.put("D", 0); dist.put("F", 0);
        for (CourseData d : records) {
            String grade = d.getGrade();
            dist.put(grade, dist.get(grade) + 1);
        }
        return dist;
    }

    public List<CourseData> top(int count) {
        List<CourseData> sorted = new ArrayList<>(records);
        sorted.sort((a, b) -> Integer.compare(b.score, a.score));
        if (count >= sorted.size()) return sorted;
        return sorted.subList(0, count);
    }

    public void removeBelow(int minimum) {
        records.removeIf(d -> d.score < minimum);
    }

    public static void main(String[] args) {
        CourseCollectionManager manager = new CourseCollectionManager();
        // 包含重複學號、同分與空白 tag 的 6 筆測試資料
        manager.add(new CourseData("S01", "Java", 95));
        manager.add(new CourseData("S02", "Web", 85));
        manager.add(new CourseData("S01", "AI", 85)); 
        manager.add(new CourseData("S03", "Java", 50));
        manager.add(new CourseData("S04", "", 75)); 
        manager.add(new CourseData("S05", "AI", 95)); 

        System.out.println("成績分佈: " + manager.scoreDistribution());
        System.out.println("排名前三: " + manager.top(3));
        
        manager.removeBelow(60);
        System.out.println("移除 60 分以下後，成績分佈: " + manager.scoreDistribution());
    }
}