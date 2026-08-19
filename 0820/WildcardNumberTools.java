import java.util.*;

public class WildcardNumberTools {
    public static double average(List<? extends Number> v) {
        if (v == null || v.isEmpty()) return 0.0;
        double sum = 0;
        for (Number n : v) sum += n.doubleValue();
        return sum / v.size();
    }
    
    public static double maximum(List<? extends Number> v) {
        if (v == null || v.isEmpty()) return Double.NaN;
        double max = Double.NEGATIVE_INFINITY;
        for (Number n : v) max = Math.max(max, n.doubleValue());
        return max;
    }
    
    public static void addRange(List<? super Integer> target, int start, int end) {
        if (target != null && start <= end) {
            for (int i = start; i <= end; i++) target.add(i);
        }
    }

    // 補上主程式，避免終端機執行時報錯
    public static void main(String[] args) {
        System.out.println("=== Wildcard 數值工具測試 ===");
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);
        
        System.out.printf("整數平均: %.1f%n", average(ints));
        System.out.printf("浮點數最大值: %.1f%n", maximum(doubles));
        
        List<Number> targetList = new ArrayList<>();
        addRange(targetList, 10, 12);
        System.out.printf("寫入範圍後的清單: %s%n", targetList);
    }
}