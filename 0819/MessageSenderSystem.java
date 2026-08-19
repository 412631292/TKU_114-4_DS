interface MessageSender {
    void send(String receiver, String message);
}

class EmailSender implements MessageSender {
    public void send(String r, String m) { System.out.println("Email 給 " + r + ": " + m); }
}

class SmsSender implements MessageSender {
    public void send(String r, String m) { System.out.println("簡訊 給 " + r + ": " + m); }
}

class ConsoleSender implements MessageSender {
    public void send(String r, String m) { System.out.println("Console [" + r + "]: " + m); }
}

public class MessageSenderSystem {
    public static void notify(MessageSender sender, String receiver, String message) {
        if (receiver == null || receiver.isBlank() || message == null || message.isBlank()) {
            System.out.println("錯誤：收件人或訊息不得為空！");
            return;
        }
        sender.send(receiver, message); // 只依賴 Interface，未來新增 Sender 不用改這裡
    }

    public static void main(String[] args) {
        notify(new EmailSender(), "Alice", "會議延期");
        notify(new SmsSender(), "", "空白測試"); // 觸發防呆
    }
}