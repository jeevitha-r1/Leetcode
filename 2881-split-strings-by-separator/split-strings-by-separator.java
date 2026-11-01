import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        // Pre-allocate a reasonable capacity to reduce array resizing overhead
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            int n = word.length();
            int start = 0;
            
            for (int i = 0; i <= n; i++) {
                // When we hit the separator character or reach the end of the word
                if (i == n || word.charAt(i) == separator) {
                    // Extract the substring if it's not empty
                    if (i > start) {
                        result.add(word.substring(start, i));
                    }
                    // Move past the separator for the next token
                    start = i + 1;
                }
            }
        }
        
        return result;
    }
}