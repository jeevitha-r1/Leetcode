class Solution {
    public String gcdOfStrings(String str1, String str2) {
        // Check if concatenated strings are equal; if not, no common divisor exists
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        
        // Find the GCD of the lengths of both strings
        int gcdLength = gcd(str1.length(), str2.length());
        
        // Return the prefix of that length
        return str1.substring(0, gcdLength);
    }
    
    // Helper method to calculate the greatest common divisor using Euclidean algorithm
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}