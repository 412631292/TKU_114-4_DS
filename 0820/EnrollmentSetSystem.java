import java.util.*;

class Enrollment {
    private String studentId;
    private String courseCode;

    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Enrollment other = (Enrollment) obj;
        return Objects.equals(studentId, other.studentId) && 
               Objects.equals(courseCode, other.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() { return studentId + "-" + courseCode; }
}

public class EnrollmentSetSystem {
    public static void main(String[] args) {
        Set<Enrollment> enrollments = new HashSet<>();
        
        System.out.println("加入 Alice-Java: " + enrollments.add(new Enrollment("A01", "Java")));
        System.out.println("加入 Alice-Web: " + enrollments.add(new Enrollment("A01", "Web")));
        System.out.println("重複加入 Alice-Java: " + enrollments.add(new Enrollment("A01", "Java"))); // 應為 false

        Enrollment target = new Enrollment("A01", "Java");
        System.out.println("\n測試 contains: " + enrollments.contains(target));
        System.out.println("測試 remove: " + enrollments.remove(target));
        System.out.println("目前集合: " + enrollments);
    }
}