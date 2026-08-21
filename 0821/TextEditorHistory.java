import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {
    private Deque<String> undoStack = new ArrayDeque<>();
    private Deque<String> redoStack = new ArrayDeque<>();

    public void type(String text) {
        undoStack.push(text);
        redoStack.clear(); // 新增操作必須清空 redo 歷程
        printState("Type: " + text);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(undoStack.pop());
            printState("Undo");
        } else {
            System.out.println("[Undo 失敗] 無法再復原，Undo Stack 為空。");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(redoStack.pop());
            printState("Redo");
        } else {
            System.out.println("[Redo 失敗] 無法重做，Redo Stack 為空。");
        }
    }

    private void printState(String action) {
        System.out.printf("[%s] Undo Stack: %s | Redo Stack: %s%n", action, undoStack, redoStack);
    }

    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();
        
        System.out.println("=== 文字編輯 Undo/Redo 測試 ===");
        editor.type("Hello");
        editor.type("World");
        editor.undo();
        editor.type("Java"); // 這裡會觸發 redoStack 清空
        editor.redo();       // 應該失敗
        editor.undo();
        editor.undo();
        editor.undo();       // 應該失敗 (空 stack)
        editor.redo();
    }
}