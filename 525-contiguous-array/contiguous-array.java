import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findMaxLength(int[] nums) {
        // Map to store the first occurrence index of each running sum
        Map<Integer, Integer> map = new HashMap<>();
        // Base case: sum 0 occurs at index -1
        map.put(0, -1);
        
        int maxLength = 0;
        int runningSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // Treat 0 as -1 and 1 as +1
            runningSum += (nums[i] == 0) ? -1 : 1;
            
            // If this running sum has been seen before, it means the subarray 
            // between the previous index and current index has an equal number of 0s and 1s
            if (map.containsKey(runningSum)) {
                maxLength = Math.max(maxLength, i - map.get(runningSum));
            } else {
                // Store the first time we see this running sum to maximize length
                map.put(runningSum, i);
            }
        }
        
        return maxLength;
    }
}