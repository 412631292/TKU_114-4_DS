// 檔名：MediaProcessingSystem.java

// 定義能力介面
interface Playable {
    void play();
}

interface Compressible {
    void compress();
}

// 抽象基礎類別 MediaFile
abstract class MediaFile {
    protected String fileName;

    public MediaFile(String fileName) {
        this.fileName = fileName;
    }
    
    public String getFileName() {
        return fileName;
    }
}

// 圖片檔：只能壓縮，不能播放
class ImageFile extends MediaFile implements Compressible {
    public ImageFile(String fileName) { super(fileName); }
    
    @Override
    public void compress() {
        System.out.println("-> 壓縮圖片檔：" + getFileName() + " (降低解析度與色彩深度)");
    }
}

// 音訊檔：只能播放，不能壓縮 (假設為無損音檔不提供壓縮操作)
class AudioFile extends MediaFile implements Playable {
    public AudioFile(String fileName) { super(fileName); }
    
    @Override
    public void play() {
        System.out.println("-> 播放音訊檔：" + getFileName() + " (輸出至揚聲器)");
    }
}

// 影片檔：同時支援播放與壓縮
class VideoFile extends MediaFile implements Playable, Compressible {
    public VideoFile(String fileName) { super(fileName); }
    
    @Override
    public void play() {
        System.out.println("-> 播放影片檔：" + getFileName() + " (渲染畫面與同步音軌)");
    }

    @Override
    public void compress() {
        System.out.println("-> 壓縮影片檔：" + getFileName() + " (轉換為 H.264 編碼)");
    }
}

public class MediaProcessingSystem {
    public static void main(String[] args) {
        System.out.println("=== 媒體檔案處理系統 ===");
        
        MediaFile[] files = {
            new ImageFile("vacation.jpg"),
            new AudioFile("podcast.mp3"),
            new VideoFile("presentation.mp4")
        };

        // 檢查每個物件支援的操作，動態轉型並執行
        for (MediaFile file : files) {
            System.out.println("\n正在處理檔案: " + file.getFileName());
            
            if (file instanceof Playable p) {
                p.play();
            } else {
                System.out.println("  [無法播放此檔案格式]");
            }
            
            if (file instanceof Compressible c) {
                c.compress();
            } else {
                System.out.println("  [此檔案格式不支援壓縮]");
            }
        }
    }
}