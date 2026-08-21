class Task {
    String id;
    String name;
    public Task(String id, String name) { this.id = id; this.name = name; }
    @Override public String toString() { return "[" + id + "] " + name; }
}

class TaskNode {
    Task task;
    TaskNode next;
    public TaskNode(Task task) { this.task = task; }
}

class TaskLinkedList {
    private TaskNode head;
    private int size = 0;

    public boolean findById(String id) {
        TaskNode curr = head;
        while (curr != null) {
            if (curr.task.id.equals(id)) return true;
            curr = curr.next;
        }
        return false;
    }

    public void addFirst(Task task) {
        if (findById(task.id)) { System.out.println("新增失敗: ID 已存在 -> " + task.id); return; }
        TaskNode newNode = new TaskNode(task);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(Task task) {
        if (findById(task.id)) { System.out.println("新增失敗: ID 已存在 -> " + task.id); return; }
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = newNode;
        }
        size++;
    }

    public void insertAfter(String existingId, Task task) {
        if (findById(task.id)) { System.out.println("新增失敗: ID 已存在 -> " + task.id); return; }
        TaskNode curr = head;
        while (curr != null && !curr.task.id.equals(existingId)) {
            curr = curr.next;
        }
        if (curr != null) {
            TaskNode newNode = new TaskNode(task);
            newNode.next = curr.next;
            curr.next = newNode;
            size++;
        } else {
            System.out.println("插入失敗: 找不到目標 ID -> " + existingId);
        }
    }

    public void removeById(String id) {
        if (head == null) {
            System.out.println("刪除失敗: 清單為空");
            return;
        }
        if (head.task.id.equals(id)) {
            head = head.next; // 刪除 Head
            size--;
            System.out.println("刪除成功: " + id);
            return;
        }
        TaskNode curr = head;
        while (curr.next != null && !curr.next.task.id.equals(id)) {
            curr = curr.next;
        }
        if (curr.next != null) {
            curr.next = curr.next.next; // 刪除 Middle 或 Tail
            size--;
            System.out.println("刪除成功: " + id);
        } else {
            System.out.println("刪除失敗: 找不到 ID -> " + id);
        }
    }

    public int size() { return size; }

    public void printAll() {
        TaskNode curr = head;
        System.out.print("清單狀態: ");
        while (curr != null) {
            System.out.print(curr.task.id + " -> ");
            curr = curr.next;
        }
        System.out.println("null (Size: " + size + ")");
    }
}

public class LinkedTaskListSystem {
    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();
        
        System.out.println("=== 測試空 List 刪除 ===");
        list.removeById("T01");

        System.out.println("\n=== 測試新增操作 ===");
        list.addLast(new Task("T01", "任務 1"));
        list.addLast(new Task("T02", "任務 2"));
        list.addFirst(new Task("T00", "任務 0"));
        list.insertAfter("T01", new Task("T01.5", "任務 1.5"));
        list.addFirst(new Task("T01", "重複 ID")); 
        list.printAll();

        System.out.println("\n=== 測試刪除 Head, Middle, Tail 與 找不到 ===");
        list.removeById("T00");   // Head
        list.removeById("T01.5"); // Middle
        list.removeById("T02");   // Tail
        list.removeById("T99");   // 找不到
        list.printAll();
    }
}