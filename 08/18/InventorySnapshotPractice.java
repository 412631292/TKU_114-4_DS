import java.util.Arrays;

// Immutable 類別通常宣告為 final，防止被繼承並覆寫行為
final class InventorySnapshot {
    private final String warehouseId;
    private final int[] quantities;

    public InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId;
        
        // Defensive Copy (防禦性複製) - Constructor
        // 邊界條件：如果收到 null，建立長度為 0 的陣列
        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            // 不直接指派 reference，而是複製一份全新的陣列
            this.quantities = Arrays.copyOf(quantities, quantities.length);
        }
    }

    // Defensive Copy (防禦性複製) - Getter
    public int[] getQuantities() {
        // 回傳陣列的副本，防止外部透過 getter 拿到 reference 後修改內部資料
        return Arrays.copyOf(this.quantities, this.quantities.length);
    }

    public String getWarehouseId() {
        return warehouseId; // String 本身就是 Immutable，可以直接回傳
    }

    // 回傳總數量
    public int totalQuantity() {
        int sum = 0;
        for (int q : quantities) {
            sum += q;
        }
        return sum;
    }

    // 回傳數量為 0 的品項數
    public int outOfStockCount() {
        int count = 0;
        for (int q : quantities) {
            if (q == 0) {
                count++;
            }
        }
        return count;
    }
}

public class InventorySnapshotPractice {
    public static void main(String[] args) {
        System.out.println("=== 庫存快照 (Immutable) 測試 ===\n");

        int[] originalData = {5, 0, 3, 0};
        InventorySnapshot snapshot = new InventorySnapshot("WH-A", originalData);

        System.out.println("快照建立完成！");
        System.out.println("總數: " + snapshot.totalQuantity() + " (預期: 8)");
        System.out.println("缺貨品項數: " + snapshot.outOfStockCount() + " (預期: 2)");

        System.out.println("\n--- 防禦性複製測試 ---");
        // 嘗試惡意修改原始資料
        originalData[1] = 100;
        System.out.println("修改外部 originalData[1] = 100 後，快照總數: " + snapshot.totalQuantity() + " (不應受影響)");

        // 嘗試透過 getter 惡意修改資料
        int[] leakedData = snapshot.getQuantities();
        if (leakedData.length > 0) {
            leakedData[0] = 999;
        }
        System.out.println("修改 getQuantities() 取得的陣列後，快照總數: " + snapshot.totalQuantity() + " (不應受影響)");
        
        System.out.println("\n--- 邊界條件 (null) 測試 ---");
        InventorySnapshot nullSnapshot = new InventorySnapshot("WH-B", null);
        System.out.println("傳入 null 陣列時的總數: " + nullSnapshot.totalQuantity() + " (預期: 0)");
    }
}