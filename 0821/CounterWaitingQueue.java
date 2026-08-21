import java.util.ArrayDeque;
import java.util.Deque;

class Customer {
    private String name;
    public Customer(String name) { this.name = name; }
    @Override public String toString() { return name; }
}

public class CounterWaitingQueue {
    private Deque<Customer> queue = new ArrayDeque<>();

    public void enqueue(Customer customer) {
        queue.offer(customer);
        System.out.println(customer + " 加入等候隊列");
    }

    public Customer peekNext() {
        return queue.peek();
    }

    public Customer serveNext() {
        return queue.poll();
    }

    public int waitingCount() {
        return queue.size();
    }

    public static void main(String[] args) {
        CounterWaitingQueue counter = new CounterWaitingQueue();
        
        counter.enqueue(new Customer("Alice"));
        counter.enqueue(new Customer("Bob"));
        
        System.out.println("目前等候人數: " + counter.waitingCount());
        System.out.println("下一位顧客是: " + counter.peekNext());
        System.out.println("服務顧客: " + counter.serveNext());
        System.out.println("服務顧客: " + counter.serveNext());
        System.out.println("空隊列處理 (不應報錯): " + counter.serveNext());
    }
}