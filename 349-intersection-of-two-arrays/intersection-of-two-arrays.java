import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        List<Integer> result = new ArrayList<>();
        for (int num : nums2) {
            // If the set contains the number, add it to result and remove it 
            // from the set to avoid adding duplicates if nums2 has duplicate values.
            if (set.contains(num)) {
                result.add(num);
                set.remove(num); 
            }
        }

        // Convert List<Integer> to int[]
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        
        return res;
    }
}