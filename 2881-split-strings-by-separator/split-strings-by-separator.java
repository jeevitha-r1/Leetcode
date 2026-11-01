import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            // Escape special regex characters like '|', '.', '$', etc., or split by character literal
            // In Java, String.split() treats the argument as a regular expression.
            String regex = "\\" + separator; 
            String[] parts = word.split(regex);
            
            for (String part : parts) {
                // Exclude empty strings
                if (!part.isEmpty()) {
                    result.add(part);
                }
            }
        }
        
        return result;
    }
}