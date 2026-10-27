class Solution {
    public int findMinimumOperations(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        
        int minLen = len1 < len2 ? (len1 < len3 ? len1 : len3) : (len2 < len3 ? len2 : len3);
        
        if (minLen == 0 || s1.charAt(0) != s2.charAt(0) || s2.charAt(0) != s3.charAt(0)) {
            return -1;
        }
        
        int commonLength = 1;
        while (commonLength < minLen && 
               s1.charAt(commonLength) == s2.charAt(commonLength) && 
               s2.charAt(commonLength) == s3.charAt(commonLength)) {
            commonLength++;
        }
        
        return (len1 - commonLength) + (len2 - commonLength) + (len3 - commonLength);
    }
}