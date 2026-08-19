// 檔名：ReportExporterFactory.java
import java.util.Arrays;

// 定義輸出介面
interface ReportExporter {
    void export(String title, int[] values);
}

class CsvExporter implements ReportExporter {
    public void export(String title, int[] values) {
        // 處理 values 為 null 的防呆情況
        String data = (values == null) ? "" : Arrays.toString(values).replaceAll("[\\[\\] ]", "");
        System.out.println("【CSV 輸出】");
        System.out.println("title," + title);
        System.out.println("data," + data + "\n");
    }
}

class JsonExporter implements ReportExporter {
    public void export(String title, int[] values) {
        String data = (values == null) ? "[]" : Arrays.toString(values);
        System.out.println("【JSON 輸出】");
        System.out.printf("{ \"title\": \"%s\", \"data\": %s }%n%n", title, data);
    }
}

class TextExporter implements ReportExporter {
    public void export(String title, int[] values) {
        String data = (values == null) ? "無資料" : Arrays.toString(values);
        System.out.println("【TEXT 輸出】");
        System.out.printf("報表標題: %s | 內容數值: %s%n%n", title, data);
    }
}

public class ReportExporterFactory {
    
    // Factory Method：根據字串產生對應的 Exporter
    public static ReportExporter createExporter(String format) {
        if (format == null) return new TextExporter();
        
        switch (format.toLowerCase()) {
            case "csv": return new CsvExporter();
            case "json": return new JsonExporter();
            default: return new TextExporter(); // 不支援的 format 回傳 TextExporter
        }
    }

    // 主流程：只依賴 ReportExporter 介面，絕不使用 instanceof
    public static void exportReport(ReportExporter exporter, String title, int[] values) {
        exporter.export(title, values);
    }

    public static void main(String[] args) {
        int[] reportData = {100, 250, 400};

        System.out.println("=== 報表輸出 Factory 系統 ===");
        
        // 測試支援的格式
        exportReport(createExporter("csv"), "第一季營收", reportData);
        exportReport(createExporter("json"), "第二季營收", reportData);
        
        // 測試不支援的格式與 null 資料
        exportReport(createExporter("xml"), "第三季營收", reportData);
        exportReport(createExporter("json"), "空白報表", null); 
    }
}