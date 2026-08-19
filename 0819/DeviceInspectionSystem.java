class Device {
    public void runDiagnostic() { System.out.println("設備一般檢測中..."); }
}
class Laptop extends Device {
    public void runDiagnostic() { System.out.println("筆電：硬碟與記憶體檢測..."); }
}
class Printer extends Device {
    public void runDiagnostic() { System.out.println("印表機：墨水與紙張檢測..."); }
    public void cleanPrintHead() { System.out.println("-> 正在清潔印表機噴頭！"); }
}
class Router extends Device {
    public void runDiagnostic() { System.out.println("路由器：網路封包檢測..."); }
}

public class DeviceInspectionSystem {
    public static void main(String[] args) {
        Device[] devices = { new Laptop(), new Printer(), new Router(), new Printer() };
        
        for (Device d : devices) {
            d.runDiagnostic();
            // Java 16+ Pattern Matching: 安全判斷並自動轉型為 p
            if (d instanceof Printer p) {
                p.cleanPrintHead();
            }
        }
    }
}