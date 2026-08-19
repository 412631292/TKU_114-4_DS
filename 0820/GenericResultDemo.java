// 這是我們剛剛寫好的藍圖類別
class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}

// 補上主程式類別 (必須與檔名 GenericResultDemo.java 完全一致)
public class GenericResultDemo {
    public static void main(String[] args) {
        System.out.println("=== 泛型結果封裝測試 ===");

        // 測試一：成功的字串結果
        Result<String> res1 = new Result<>(true, "讀取成功", "這是泛型字串資料");
        System.out.printf("狀態: %s | 資料: %s%n", res1.getMessage(), res1.getData());

        // 測試二：失敗的整數結果 (正確處理 null)
        Result<Integer> res2 = new Result<>(false, "找不到數字", null);
        System.out.printf("狀態: %s | 資料: %s%n", res2.getMessage(), res2.getData());
    }
}