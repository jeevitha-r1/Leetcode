import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int similarPairs(String[] words) {
        Map<Set<Character>, Integer> countMap = new HashMap<>();
        
        for (String word : words) {
            // Find the unique set of characters for each word
            Set<Character> charSet = new HashSet<>();
            for (char c : word.toCharArray()) {
                charSet.add(c);
            }
            
            // Increment the frequency of this character set in the map
            countMap.put(charSet, countMap.getOrDefault(charSet, 0) + 1);
        }
        
        int totalPairs = 0;
        // Calculate the number of pairs for each unique signature using combinations: k * (k - 1) / 2
        for (int freq : countMap.values()) {
            if (freq > 1) {
                totalPairs += (freq * (freq - 1)) / 2;
            }
        }
        
        return totalPairs;
    }
}