class Transaction {
    private int sequence;
    private String type;
    private double amount;

    public Transaction(int sequence, String type, double amount) {
        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
    }

    public int getSequence() { return sequence; }
    public String getType() { return type; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return String.format("Seq: %04d | 類型: %-8s | 金額: %.2f", sequence, type, amount);
    }
}

class HistoryWallet {
    private String walletId;
    private double balance;
    private Transaction[] transactions;
    private int currentTxCount;
    private static int globalSeq = 1; // 產生全域唯一流水號

    public HistoryWallet(String walletId, int maxHistory) {
        this.walletId = walletId;
        this.balance = 0.0;
        this.transactions = new Transaction[maxHistory];
        this.currentTxCount = 0;
    }

    public boolean deposit(double amount) {
        if (amount > 0 && currentTxCount < transactions.length) {
            balance += amount;
            transactions[currentTxCount++] = new Transaction(globalSeq++, "Deposit", amount);
            return true;
        }
        return false;
    }

    public boolean pay(double amount) {
        if (amount > 0 && balance >= amount && currentTxCount < transactions.length) {
            balance -= amount;
            transactions[currentTxCount++] = new Transaction(globalSeq++, "Pay", amount);
            return true;
        }
        return false;
    }

    // 跨錢包轉帳 (雙方都會留下紀錄)
    public boolean transferTo(HistoryWallet target, double amount) {
        // 檢查雙方陣列是否已滿，以及餘額是否充足
        if (target != null && target != this && amount > 0 && this.balance >= amount) {
            if (this.currentTxCount < this.transactions.length && target.currentTxCount < target.transactions.length) {
                this.balance -= amount;
                target.balance += amount;
                
                int seq = globalSeq++;
                this.transactions[this.currentTxCount++] = new Transaction(seq, "TransferOut", amount);
                target.transactions[target.currentTxCount++] = new Transaction(seq, "TransferIn", amount);
                return true;
            }
        }
        return false;
    }

    public Transaction findTransaction(int sequence) {
        for (int i = 0; i < currentTxCount; i++) {
            if (transactions[i].getSequence() == sequence) {
                return transactions[i];
            }
        }
        return null;
    }

    public double totalByType(String type) {
        double total = 0;
        for (int i = 0; i < currentTxCount; i++) {
            if (transactions[i].getType().equals(type)) {
                total += transactions[i].getAmount();
            }
        }
        return total;
    }

    public void printStatement() {
        System.out.println("\n--- Statement for " + walletId + " ---");
        System.out.println("Current Balance: " + balance);
        for (int i = 0; i < currentTxCount; i++) {
            System.out.println(transactions[i]);
        }
    }
}

public class WalletHistoryManager {
    public static void main(String[] args) {
        HistoryWallet w1 = new HistoryWallet("W-A", 10);
        HistoryWallet w2 = new HistoryWallet("W-B", 10);

        w1.deposit(2000);
        w1.pay(500);
        w1.transferTo(w2, 300);

        w1.printStatement();
        w2.printStatement();

        System.out.println("\n--- 統計與查詢功能測試 ---");
        System.out.println("W-A Deposit 總計: " + w1.totalByType("Deposit"));
        System.out.println("查詢 W-A 的交易流水號 2: " + w1.findTransaction(2));
    }
}