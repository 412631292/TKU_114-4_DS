// 定義 Book 類別
class Book {
    private String id;
    private String title;
    private double price;
    private int stock;

    public Book(String id, String title, double price, int stock) {
        this.id = id;
        this.title = title;
        this.price = Math.max(price, 0); // 確保價格不為負
        this.stock = Math.max(stock, 0); // 確保庫存不為負
    }

    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getTitle() { return title; }

    // 計算單一書籍的總價值
    public double getTotalValue() {
        return price * stock;
    }

    @Override
    public String toString() {
        return String.format("[書號: %s | 書名: %-10s | 價格: %6.2f | 庫存: %3d]", id, title, price, stock);
    }
}

public class BookArrayReport {
    public static void main(String[] args) {
        System.out.println("=== 書籍庫存報表系統 ===\n");

        // 建立 Book 陣列，保存四本書的完整物件
        Book[] books = {
            new Book("B001", "Java基礎", 450.0, 10),
            new Book("B002", "資料結構", 550.0, 2),
            new Book("B003", "演算法", 600.0, 5),
            new Book("B004", "設計模式", 500.0, 3)
        };

        // 1. 輸出所有書籍
        System.out.println("--- 所有書籍清單 ---");
        for (Book book : books) {
            System.out.println(book);
        }

        // 變數初始化，準備進行統計
        double totalInventoryValue = 0;
        Book mostExpensiveBook = books[0];

        System.out.println("\n--- 庫存小於或等於 3 的書籍 ---");
        for (Book book : books) {
            // 2. 計算庫存總價值
            totalInventoryValue += book.getTotalValue();

            // 3. 找出價格最高的書
            if (book.getPrice() > mostExpensiveBook.getPrice()) {
                mostExpensiveBook = book;
            }

            // 4. 輸出庫存小於或等於 3 的書
            if (book.getStock() <= 3) {
                System.out.println(book);
            }
        }

        System.out.println("\n--- 統計報表 ---");
        System.out.printf("庫存總價值: %.2f\n", totalInventoryValue);
        System.out.println("最高價書籍: " + mostExpensiveBook.getTitle() + " (價格: " + mostExpensiveBook.getPrice() + ")");
    }
}