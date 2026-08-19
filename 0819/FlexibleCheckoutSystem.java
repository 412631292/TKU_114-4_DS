// 檔名：FlexibleCheckoutSystem.java

// --- 策略 1：計價政策 (PricingPolicy) ---
interface PricingPolicy {
    double calculateFinalPrice(double originalPrice);
}

class RegularPricing implements PricingPolicy {
    public double calculateFinalPrice(double price) { return price; }
}

class VipPricing implements PricingPolicy {
    public double calculateFinalPrice(double price) { return price * 0.85; } // VIP 八五折
}

class Discount2000Pricing implements PricingPolicy {
    public double calculateFinalPrice(double price) {
        return (price >= 2000) ? (price - 300) : price; // 滿 2000 折 300
    }
}

// --- 策略 2：通知管道 (NotificationChannel) ---
interface NotificationChannel {
    boolean send(String message);
}

class EmailChannel implements NotificationChannel {
    public boolean send(String msg) { 
        System.out.println("[Email 通知]: " + msg); return true; 
    }
}

class SmsChannel implements NotificationChannel {
    public boolean send(String msg) { 
        System.out.println("[SMS 簡訊]: " + msg); return true; 
    }
}

class ConsoleChannel implements NotificationChannel {
    public boolean send(String msg) { 
        System.out.println("[系統終端]: " + msg); return true; 
    }
}

// --- 結帳結果保存容器 ---
class CheckoutResult {
    public String orderId;
    public double originalPrice;
    public double finalPrice;
    public boolean notificationStatus;

    public CheckoutResult(String id, double orig, double finalP, boolean status) {
        this.orderId = id; this.originalPrice = orig; this.finalPrice = finalP; this.notificationStatus = status;
    }

    @Override
    public String toString() {
        return String.format("結帳結果 [訂單: %s | 原價: %.0f | 最終結帳: %.0f | 通知成功: %b]", 
                orderId, originalPrice, finalPrice, notificationStatus);
    }
}

// --- 結帳主系統 ---
public class FlexibleCheckoutSystem {
    
    // 結帳方法：將策略 (Pricing, Channel) 注入
    public static CheckoutResult checkout(String orderId, double originalPrice, 
                                          PricingPolicy pricing, NotificationChannel channel) {
        // 1. 計算費用
        double finalPrice = pricing.calculateFinalPrice(originalPrice);
        
        // 2. 發送通知
        String msg = String.format("訂單 %s 已成立，實付金額: %.0f", orderId, finalPrice);
        boolean status = channel.send(msg);
        
        // 3. 回傳完整結果包裝
        return new CheckoutResult(orderId, originalPrice, finalPrice, status);
    }

    public static void main(String[] args) {
        System.out.println("=== 靈活結帳與通知系統測試 ===\n");

        // 測試六種不同組合
        System.out.println(checkout("ORD-001", 1000, new RegularPricing(), new ConsoleChannel()));
        System.out.println(checkout("ORD-002", 1000, new VipPricing(), new EmailChannel()));
        System.out.println(checkout("ORD-003", 2500, new Discount2000Pricing(), new SmsChannel()));
        
        System.out.println(checkout("ORD-004", 1500, new Discount2000Pricing(), new ConsoleChannel())); // 未達滿減條件
        System.out.println(checkout("ORD-005", 3000, new VipPricing(), new SmsChannel()));
        System.out.println(checkout("ORD-006", 500, new RegularPricing(), new EmailChannel()));
    }
}