class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getName() { return name; }
    
    // 新增 getId() 方法，消除黃色警告
    public String getId() { return id; }
}

class OrderItem {
    private String productName;
    private double price;
    private int quantity;

    public OrderItem(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = Math.max(price, 0);
        this.quantity = Math.max(quantity, 0);
    }
    
    public double getSubtotal() { return price * quantity; }
    public int getQuantity() { return quantity; }
    public String getProductName() { return productName; }
}

class CustomerOrder {
    private String orderId;
    private Customer customer;
    private OrderItem[] items;
    private int itemCount;

    public CustomerOrder(String orderId, Customer customer, int maxItems) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new OrderItem[maxItems];
        this.itemCount = 0;
    }

    public boolean addItem(OrderItem item) {
        if (item != null && itemCount < items.length) {
            items[itemCount++] = item;
            return true;
        }
        return false;
    }

    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += items[i].getSubtotal();
        }
        return total;
    }

    public void printSummary() {
        // 第一行輸出：訂單編號與顧客名稱
        System.out.println("訂單編號: " + orderId + " | 顧客: " + customer.getName());
        
        int totalQuantity = 0;
        for (int i = 0; i < itemCount; i++) {
            // 使用 printf 與 %n 正確換行
            System.out.printf("- %s (數量: %d, 小計: %.2f)%n", 
                    items[i].getProductName(), items[i].getQuantity(), items[i].getSubtotal());
            totalQuantity += items[i].getQuantity();
        }
        
        // 輸出總計，同樣使用 printf 與 %n
        System.out.printf("總品項數: %d | 訂單總額: %.2f%n", totalQuantity, calculateTotal());
    }
}

public class CustomerOrderSystem {
    public static void main(String[] args) {
        System.out.println("=== 訂單與顧客管理測試 ===");
        
        Customer customer = new Customer("C-123", "Bob");
        CustomerOrder order = new CustomerOrder("ORD-999", customer, 5);
        
        order.addItem(new OrderItem("無線滑鼠", 800, 1));
        order.addItem(new OrderItem("機械鍵盤", 2500, 2));
        
        order.printSummary();
    }
}