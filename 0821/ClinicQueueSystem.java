import java.util.*;

class Patient {
    String id;
    String name;
    
    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override 
    public String toString() { 
        return String.format("[%s] %s", id, name); 
    }
}

public class ClinicQueueSystem {
    private Queue<Patient> waitingQueue = new LinkedList<>();
    private List<Patient> completedList = new ArrayList<>();

    public void register(Patient p) {
        waitingQueue.offer(p);
        System.out.println("掛號成功: " + p);
    }

    public void cancel(String id) {
        // 取消指定病歷號
        boolean removed = waitingQueue.removeIf(p -> p.id.equals(id));
        System.out.println(removed ? "取消掛號成功: " + id : "取消失敗，找不到病歷號: " + id);
    }

    public void callNext() {
        Patient p = waitingQueue.poll();
        if (p != null) {
            completedList.add(p);
            System.out.println("請進診間看診: " + p);
        } else {
            System.out.println("目前無等候病患。");
        }
    }

    public void viewNext() {
        Patient p = waitingQueue.peek();
        System.out.println(p != null ? "下一位病患是: " + p : "目前無等候病患。");
    }

    public void printCompleted() {
        System.out.println("當日完成清單: " + completedList);
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();
        
        System.out.println("=== 診所掛號系統測試 ===");
        clinic.register(new Patient("P01", "Alice"));
        clinic.register(new Patient("P02", "Bob"));
        clinic.register(new Patient("P03", "Charlie"));
        
        clinic.viewNext();
        clinic.cancel("P02"); // 取消中間的 Bob
        
        clinic.callNext();
        clinic.callNext();
        clinic.callNext();    // 應該顯示無病患
        
        clinic.printCompleted();
    }
}