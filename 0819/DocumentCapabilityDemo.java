interface Exportable { void exportDoc(); }
interface Compressible { void compressDoc(); }

class BackupDocument implements Exportable, Compressible {
    public void exportDoc() { System.out.println("執行：匯出文件..."); }
    public void compressDoc() { System.out.println("執行：壓縮文件..."); }
}

public class DocumentCapabilityDemo {
    public static void main(String[] args) {
        BackupDocument doc = new BackupDocument();
        Exportable expRef = doc;
        Compressible compRef = doc;
        
        System.out.println("兩個 Reference 指向同一物件嗎？ " + (expRef == compRef));
        
        expRef.exportDoc();  // expRef 無法呼叫 compressDoc()
        compRef.compressDoc(); // compRef 無法呼叫 exportDoc()
    }
}