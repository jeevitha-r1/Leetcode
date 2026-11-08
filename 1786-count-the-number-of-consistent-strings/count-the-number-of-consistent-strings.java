class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        // Create a frequency/boolean array to keep track of allowed characters
        boolean[] isAllowed = new boolean[26];
        for (char c : allowed.toCharArray()) {
            isAllowed[c - 'a'] = true;
        }
        
        int count = 0;
        
        for (String word : words) {
            boolean consistent = true;
            for (int i = 0; i < word.length(); i++) {
                if (!isAllowed[word.charAt(i) - 'a']) {
                    consistent = false;
                    break; // If any character is not allowed, this word is invalid
                }
            }
            if (consistent) {
                count++;
            }
        }
        
        return count;
    }
}