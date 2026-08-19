import java.util.*;

public class WordIndexSystem {
    public static void main(String[] args) {
        String[] sentences = {
            "Hello, world.",
            "The World is beautiful.",
            "Hello again, Java."
        };

        Map<String, Integer> wordCount = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        for (String sentence : sentences) {
            // 忽略大小寫與句點、逗號，並以空白分割
            String[] words = sentence.toLowerCase().replaceAll("[,.]", "").split("\\s+");
            for (String w : words) {
                if (w.isEmpty()) continue;
                uniqueWords.add(w);
                wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);
            }
        }

        System.out.println("=== 出現至少兩次的單字 ===");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() >= 2) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " 次");
            }
        }
    }
}