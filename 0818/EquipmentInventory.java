// 定義 Equipment 類別
class Equipment {
    // 1. 三個 private field，保護資料不被外部直接修改
    private String id;
    private String name;
    private int availableCount;

    // 2. Constructor (建構子)
    public Equipment(String id, String name, int availableCount) {
        // 檢查 id 是否為 null 或空白，若是則設為 "Unknown"
        if (id == null || id.trim().isEmpty()) {
            this.id = "Unknown";
        } else {
            this.id = id;
        }

        // 檢查 name 是否為 null 或空白，若是則設為 "Unknown"
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unknown";
        } else {
            this.name = name;
        }

        // 檢查數量是否為負數，若是則設為 0
        this.availableCount = Math.max(availableCount, 0);
    }

    // 3. borrowOne()：借用一個設備
    public boolean borrowOne() {
        if (this.availableCount > 0) {
            this.availableCount--; // 庫存減 1
            return true;           // 借用成功
        }
        return false;              // 庫存不足，借用失敗
    }

    // 4. returnItems()：歸還設備
    public void returnItems(int quantity) {
        // 只有正數才加入庫存，防止傳入負數導致庫存減少
        if (quantity > 0) {
            this.availableCount += quantity;
        }
    }

    // 5. toString()：格式化輸出設備資訊
    @Override
    public String toString() {
        return String.format("[設備編號: %s | 名稱: %s | 可借數量: %d]", id, name, availableCount);
    }
}

// 主程式類別
public class EquipmentInventory {
    public static void main(String[] args) {
        System.out.println("=== 設備庫存系統測試開始 ===\n");

        // 建立測試設備 1：正常設備 (只剩 1 個庫存)
        Equipment laptop = new Equipment("EQ-001", "MacBook Pro", 1);
        System.out.println("新增設備 1: " + laptop.toString());

        // 建立測試設備 2：測試防呆機制 (空編號、空名稱、負數庫存)
        Equipment projector = new Equipment("", "  ", -5);
        System.out.println("新增設備 2: " + projector.toString());
        
        System.out.println("\n--- 測試：借用成功與失敗 ---");
        
        // 測試設備 1 (庫存 1)
        System.out.println("嘗試借用 MacBook Pro 第一台...");
        boolean success1 = laptop.borrowOne();
        System.out.println("結果: " + (success1 ? "借用成功！" : "借用失敗！") + " 目前狀態: " + laptop);

        System.out.println("嘗試借用 MacBook Pro 第二台...");
        boolean success2 = laptop.borrowOne();
        System.out.println("結果: " + (success2 ? "借用成功！" : "借用失敗 (庫存不足)！") + " 目前狀態: " + laptop);

        System.out.println("\n--- 測試：歸還設備 ---");
        
        // 測試歸還負數 (防呆測試)
        System.out.println("嘗試歸還 -3 台 MacBook Pro...");
        laptop.returnItems(-3);
        System.out.println("目前狀態 (不應改變): " + laptop);

        // 測試正常歸還
        System.out.println("嘗試歸還 5 台 MacBook Pro...");
        laptop.returnItems(5);
        System.out.println("目前狀態: " + laptop);

        System.out.println("\n=== 設備庫存系統測試結束 ===");
    }
}