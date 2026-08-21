import java.util.*;

class DeliveryItem {
    String id;
    String destination;
    
    public DeliveryItem(String id, String destination) {
        this.id = id;
        this.destination = destination;
    }
    
    @Override 
    public String toString() { 
        return id + " -> " + destination; 
    }
}

public class DeliveryWorkflowSystem {
    private Map<String, DeliveryItem> itemMap = new HashMap<>();
    private Deque<DeliveryItem> waitingQueue = new ArrayDeque<>();
    private Deque<DeliveryItem> completedStack = new ArrayDeque<>();

    public void addDelivery(String id, String dest) {
        if (itemMap.containsKey(id)) {
            System.out.println("新增失敗，配送編號已存在: " + id);
            return;
        }
        DeliveryItem item = new DeliveryItem(id, dest);
        itemMap.put(id, item);
        waitingQueue.offer(item);
        System.out.println("新增配送: " + item);
    }

    public void processNext() {
        DeliveryItem item = waitingQueue.poll();
        if (item != null) {
            completedStack.push(item);
            System.out.println("處理完成: " + item);
        } else {
            System.out.println("目前無等待中配送。");
        }
    }

    public void undo() {
        if (!completedStack.isEmpty()) {
            DeliveryItem item = completedStack.pop();
            waitingQueue.addFirst(item); // 將完成的放回等待序列最前方
            System.out.println("復原配送狀態: " + item);
        } else {
            System.out.println("無可復原的紀錄。");
        }
    }

    public void search(String id) {
        DeliveryItem item = itemMap.get(id);
        System.out.println(item != null ? "查詢結果: " + item : "查無此編號: " + id);
    }

    public void printStats() {
        System.out.printf("統計 -> 系統總件數: %d | 等待中: %d | 已完成: %d%n",
                itemMap.size(), waitingQueue.size(), completedStack.size());
    }

    public static void main(String[] args) {
        DeliveryWorkflowSystem sys = new DeliveryWorkflowSystem();
        
        System.out.println("=== 物流工作流程測試 ===");
        sys.addDelivery("D01", "台北市");
        sys.addDelivery("D02", "台中市");
        sys.addDelivery("D01", "高雄市"); // 測試重複 ID
        
        sys.processNext();
        sys.printStats();
        
        sys.undo();
        sys.search("D01");
        sys.printStats();
    }
}