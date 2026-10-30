class Solution {
    public boolean isIsomorphic(String s, String t) {
        // Arrays to store the last seen positions of characters in s and t
        // Initializing with -1 since 0 is a valid index
        int[] mapS = new int[256];
        int[] mapT = new int[256];
        
        for (int i = 0; i < 256; i++) {
            mapS[i] = -1;
            mapT[i] = -1;
        }
        
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            
            // If the mappings don't match, they are not isomorphic
            if (mapS[charS] != mapT[charT]) {
                return false;
            }
            
            // Record the current position (using i + 1 to avoid confusion with initial -1)
            mapS[charS] = i + 1;
            mapT[charT] = i + 1;
        }
        
        return true;
    }
}