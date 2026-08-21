import java.util.*;

class ServiceTicket {
    String id;
    String request;
    public ServiceTicket(String id, String request) { this.id = id; this.request = request; }
    @Override public String toString() { return "[" + id + "] " + request; }
}

public class ServiceCenterWorkflow {
    private Map<String, ServiceTicket> ticketMap = new HashMap<>();
    private Deque<ServiceTicket> waitingQueue = new ArrayDeque<>();
    private Deque<ServiceTicket> completedStack = new ArrayDeque<>();
    private Set<String> activeIds = new HashSet<>();

    public void createTicket(String id, String request) {
        if (activeIds.contains(id)) {
            System.out.println("建立失敗，重複的 ID: " + id);
            return;
        }
        ServiceTicket ticket = new ServiceTicket(id, request);
        ticketMap.put(id, ticket);
        waitingQueue.offer(ticket);
        activeIds.add(id);
        System.out.println("建立工單: " + ticket);
    }

    public void processNext() {
        ServiceTicket ticket = waitingQueue.poll();
        if (ticket != null) {
            completedStack.push(ticket);
            System.out.println("處理完成: " + ticket);
        } else {
            System.out.println("目前無等待中的工單。");
        }
    }

    public void cancelWaiting(String id) {
        ServiceTicket ticket = ticketMap.get(id);
        // 只能作用於尚未處理 (存在於 waitingQueue) 的 ticket
        if (ticket != null && waitingQueue.contains(ticket)) {
            waitingQueue.remove(ticket);
            activeIds.remove(id); // 從防呆池中釋放
            ticketMap.remove(id);
            System.out.println("已取消等待: " + id);
        } else {
            System.out.println("取消失敗，找不到或非等待中 ID: " + id);
        }
    }

    public void undoLastCompletion() {
        if (!completedStack.isEmpty()) {
            ServiceTicket ticket = completedStack.pop();
            waitingQueue.addFirst(ticket); // 將最後完成的放回 waiting queue 前端
            System.out.println("Undo 成功，已退回等待區前端: " + ticket);
        } else {
            System.out.println("無可復原的處理紀錄。");
        }
    }

    public void findById(String id) {
        ServiceTicket ticket = ticketMap.get(id);
        System.out.println(ticket != null ? "查詢結果: " + ticket : "查無此 ID: " + id);
    }

    public void printSummary() {
        System.out.printf("--- 摘要 --- 等待人數: %d | 完成人數: %d%n", 
                waitingQueue.size(), completedStack.size());
    }

    public static void main(String[] args) {
        ServiceCenterWorkflow center = new ServiceCenterWorkflow();
        
        System.out.println("=== 測試建立與重複 ID ===");
        center.createTicket("TC01", "網路維修");
        center.createTicket("TC02", "硬體更換");
        center.createTicket("TC01", "重複建立");

        System.out.println("\n=== 測試取消與不存在 ID ===");
        center.cancelWaiting("TC02");
        center.cancelWaiting("TC99"); 

        System.out.println("\n=== 測試空 Queue ===");
        center.processNext(); // TC01 完成
        center.processNext(); // 應顯示空

        System.out.println("\n=== 測試連續 Undo ===");
        center.createTicket("TC03", "帳號異常");
        center.processNext(); // TC03 完成
        
        center.undoLastCompletion(); // 退回 TC03
        center.undoLastCompletion(); // 退回 TC01
        center.undoLastCompletion(); // 應顯示無可復原

        System.out.println("\n=== 最終摘要 ===");
        center.printSummary();
    }
}