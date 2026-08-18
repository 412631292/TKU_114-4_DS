import java.util.Objects;

class LibraryMember {
    private String memberId;
    private String name;
    private String email;

    public LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("LibraryMember{ID='%s', Name='%s', Email='%s'}", memberId, name, email);
    }

    // 覆寫 equals 方法
    @Override
    public boolean equals(Object obj) {
        // 1. 檢查是否為同一個記憶體位址
        if (this == obj) return true;
        
        // 2. 邊界條件：檢查 null，並確認型別是否一致 (避免 ClassCastException)
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // 3. 轉型後，只比較 memberId
        LibraryMember other = (LibraryMember) obj;
        return Objects.equals(this.memberId, other.memberId);
    }

    // 覆寫 hashCode 方法 (只要 equals 有覆寫，hashCode 必須同步覆寫)
    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}

public class MemberEqualityPractice {
    public static void main(String[] args) {
        System.out.println("=== 會員身分比較測試 ===\n");

        // 建立兩個 ID 相同，但 Email 與名稱不同的物件
        LibraryMember member1 = new LibraryMember("M-1001", "Alice", "alice@old-email.com");
        LibraryMember member2 = new LibraryMember("M-1001", "Alice Updated", "alice@new-email.com");

        System.out.println("物件 1: " + member1);
        System.out.println("物件 2: " + member2);

        System.out.println("\n--- 比較結果 ---");
        // 測試 == (比較記憶體位址)
        System.out.println("使用 '==' 比較 (記憶體位址): " + (member1 == member2));
        
        // 測試 equals (比較商業邏輯 ID)
        System.out.println("使用 'equals' 比較 (會員 ID): " + member1.equals(member2));

        // 測試邊界條件 (傳入 null)
        System.out.println("與 null 比較 (不應發生例外): " + member1.equals(null));
    }
}