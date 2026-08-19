import java.util.*;

class StoreProduct implements Comparable<StoreProduct> {
    private int id; 
    private String name; 
    private double price; 
    private int stock;

    public StoreProduct(int id, String name, double price, int stock) {
        this.id = id; this.name = name; this.price = price; this.stock = stock;
    }
    
    // 補上 Getters，避免 VS Code 出現「變數未使用」的黃色警告
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public int compareTo(StoreProduct o) { 
        return Integer.compare(this.id, o.id); 
    }

    @Override
    public String toString() {
        return String.format("[ID:%d] %-8s | 價格: %5.0f | 庫存: %2d", id, name, price, stock);
    }
}

public class ProductComparatorPractice {
    public static void main(String[] args) {
        // 包含同價 (USB/Mouse) 與同庫存 (Keyboard/Monitor) 的 5 筆資料
        List<StoreProduct> products = Arrays.asList(
            new StoreProduct(3, "Keyboard", 1500, 5),
            new StoreProduct(1, "Mouse", 800, 10),
            new StoreProduct(2, "Monitor", 5000, 5),
            new StoreProduct(4, "USB", 800, 20),
            new StoreProduct(5, "Desk", 3000, 2)
        );

        System.out.println("=== 多規則商品排序測試 ===");
        
        // 每次排序前使用 new ArrayList<>(products) 建立 copy
        List<StoreProduct> byNatural = new ArrayList<>(products);
        Collections.sort(byNatural);
        System.out.println("\n--- Natural Order (依 ID 升冪) ---");
        for (StoreProduct p : byNatural) System.out.println(p);

        List<StoreProduct> byPriceThenName = new ArrayList<>(products);
        byPriceThenName.sort(Comparator.comparingDouble(StoreProduct::getPrice)
                                       .thenComparing(StoreProduct::getName));
        System.out.println("\n--- Comparator 1 (依價格升冪 -> 名稱) ---");
        for (StoreProduct p : byPriceThenName) System.out.println(p);

        List<StoreProduct> byStockThenId = new ArrayList<>(products);
        byStockThenId.sort(Comparator.comparingInt(StoreProduct::getStock)
                                     .reversed()
                                     .thenComparingInt(StoreProduct::getId));
        System.out.println("\n--- Comparator 2 (依庫存降冪 -> ID) ---");
        for (StoreProduct p : byStockThenId) System.out.println(p);
    }
}