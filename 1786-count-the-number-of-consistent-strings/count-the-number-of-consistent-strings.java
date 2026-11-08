class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        // Step 1: Create a bitmask for the allowed characters
        int allowedMask = 0;
        for (int i = 0; i < allowed.length(); i++) {
            allowedMask |= (1 << (allowed.charAt(i) - 'a'));
        }
        
        int count = 0;
        
        // Step 2: Check each word using bitwise operations
        for (String word : words) {
            boolean consistent = true;
            for (int i = 0; i < word.length(); i++) {
                int bit = word.charAt(i) - 'a';
                // Check if the bit at position `bit` is NOT set in allowedMask
                if ((allowedMask & (1 << bit)) == 0) {
                    consistent = false;
                    break;
                }
            }
            if (consistent) {
                count++;
            }
        }
        
        return count;
    }
}