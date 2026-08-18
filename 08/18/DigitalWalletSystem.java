class DigitalWallet {
    private String walletId;
    private String owner;
    private double balance;
    private int transactionCount;

    public DigitalWallet(String walletId, String owner) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = 0.0;
        this.transactionCount = 0;
    }

    // 儲值
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            this.transactionCount++;
            return true;
        }
        return false;
    }

    // 付款
    public boolean pay(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            this.transactionCount++;
            return true;
        }
        return false;
    }

    // 退款 (邏輯同儲值，但語義不同)
    public boolean refund(double amount) {
        if (amount > 0) {
            this.balance += amount;
            this.transactionCount++;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[錢包 ID: %s | 擁有者: %s | 餘額: %.2f | 總交易次數: %d]", 
                walletId, owner, balance, transactionCount);
    }
}

public class DigitalWalletSystem {
    public static void main(String[] args) {
        System.out.println("=== 電子錢包系統測試 ===");
        DigitalWallet wallet = new DigitalWallet("W-001", "Alice");
        
        System.out.println("初始狀態: " + wallet);
        
        wallet.deposit(1000);
        System.out.println("正常儲值 1000: " + wallet);
        
        wallet.pay(300);
        System.out.println("正常付款 300: " + wallet);
        
        wallet.pay(5000);
        System.out.println("餘額不足付款 5000 (應失敗): " + wallet);
        
        wallet.deposit(-500);
        System.out.println("負數金額儲值 -500 (應失敗): " + wallet);
        
        wallet.refund(200);
        System.out.println("正常退款 200: " + wallet);
    }
}