class Solution {
    public int balancedStringSplit(String s) {
        int count = 0;
        int balance = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                balance++;
            } else {
                balance--;
            }
            
            // Whenever balance hits 0, we found a valid balanced substring
            if (balance == 0) {
                count++;
            }
        }
        
        return count;
    }
}