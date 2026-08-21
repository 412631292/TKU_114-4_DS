import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {
    private Deque<String> history = new ArrayDeque<>();

    public void visit(String url) {
        history.push(url);
        System.out.println("造訪: " + url);
    }

    public String back() {
        return history.isEmpty() ? null : history.pop();
    }

    public String current() {
        return history.isEmpty() ? null : history.peek();
    }

    public static void main(String[] args) {
        BrowserBackStack browser = new BrowserBackStack();
        
        browser.visit("google.com");
        browser.visit("github.com");
        browser.visit("stackoverflow.com");
        
        System.out.println("目前頁面: " + browser.current());
        System.out.println("返回上一頁: " + browser.back());
        System.out.println("返回上一頁: " + browser.back());
        System.out.println("返回上一頁: " + browser.back());
        System.out.println("空狀態返回 (不應報錯): " + browser.back());
    }
}