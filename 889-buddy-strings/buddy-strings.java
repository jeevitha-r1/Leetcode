class Solution {
    public boolean buddyStrings(String s, String goal) {
        // Case 1: Lengths must be equal
        if (s.length() != goal.length()) {
            return false;
        }

        // Case 2: Strings are identical
        if (s.equals(goal)) {
            // We need at least one duplicate character to swap and stay the same
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
                if (count[c - 'a'] > 1) {
                    return true;
                }
            }
            return false;
        }

        // Case 3: Strings are different, find indices where they mismatch
        int first = -1;
        int second = -1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (first == -1) {
                    first = i;
                } else if (second == -1) {
                    second = i;
                } else {
                    // More than 2 mismatches found
                    return false;
                }
            }
        }

        // We must have exactly two mismatches, and their characters must cross-match
        return (second != -1 && 
                s.charAt(first) == goal.charAt(second) && 
                s.charAt(second) == goal.charAt(first));
    }
}