// 檔名：DeliveryStrategySystem.java
interface DeliveryMethod {
    int calculateFee();
    String getEstimate();
}

class HomeDelivery implements DeliveryMethod {
    public int calculateFee() { return 120; }
    public String getEstimate() { return "預計 1-2 天內送達府上"; }
}

class StorePickup implements DeliveryMethod {
    public int calculateFee() { return 60; }
    public String getEstimate() { return "預計 3-5 天送達指定超商"; }
}

class OrderService {
    private String orderId;
    private DeliveryMethod deliveryMethod; // Composition

    public OrderService(String orderId, DeliveryMethod deliveryMethod) {
        this.orderId = orderId;
        this.deliveryMethod = deliveryMethod;
    }

    public void printOrderInfo() {
        System.out.printf("訂單 %s | 運費: %d | 說明: %s%n", 
                orderId, deliveryMethod.calculateFee(), deliveryMethod.getEstimate());
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {
        OrderService order1 = new OrderService("ORD-01", new HomeDelivery());
        OrderService order2 = new OrderService("ORD-02", new StorePickup());
        order1.printOrderInfo();
        order2.printOrderInfo();
    }
}