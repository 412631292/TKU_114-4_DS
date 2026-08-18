class Account {
    private String id;
    private double balance;

    public Account(String id, double initialBalance) {
        this.id = id;
        this.balance = Math.max(initialBalance, 0);
    }

    public String getId() { return id; }
    public double getBalance() { return balance; }
    
    public void withdraw(double amount) { this.balance -= amount; }
    public void deposit(double amount) { this.balance += amount; }
    
    @Override
    public String toString() { return String.format("[%s, 餘額: %.2f]", id, balance); }
}

class TransferService {
    public static boolean transfer(Account source, Account target, double amount) {
        // 1. 驗證 null
        if (source == null || target == null) return false;
        // 2. 驗證同帳戶 (比較記憶體位址)
        if (source == target) return false;
        // 3. 驗證金額及餘額
        if (amount <= 0 || source.getBalance() < amount) return false;

        // 驗證全部通過，執行轉帳
        source.withdraw(amount);
        target.deposit(amount);
        return true;
    }
}

public class AccountTransferService {
    public static void main(String[] args) {
        System.out.println("=== 跨帳戶轉帳服務測試 ===");
        Account acc1 = new Account("A-001", 1000);
        Account acc2 = new Account("A-002", 500);

        System.out.println("測試 1: 成功轉帳 300");
        TransferService.transfer(acc1, acc2, 300);
        System.out.println("A1: " + acc1 + " | A2: " + acc2);

        System.out.println("測試 2: 餘額不足轉帳 9999");
        TransferService.transfer(acc1, acc2, 9999);
        System.out.println("A1: " + acc1 + " | A2: " + acc2 + " (應不變)");

        System.out.println("測試 3: 同帳戶轉帳");
        TransferService.transfer(acc1, acc1, 100);
        System.out.println("A1: " + acc1 + " (應不變)");

        System.out.println("測試 4: 目標為 null");
        TransferService.transfer(acc1, null, 100);
        System.out.println("A1: " + acc1 + " (應不變)");
    }
}