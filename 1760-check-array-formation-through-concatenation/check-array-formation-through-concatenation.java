class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        // Map to store the starting element of each piece to its index in pieces
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        for (int i = 0; i < pieces.length; i++) {
            map.put(pieces[i][0], i);
        }

        int i = 0;
        while (i < arr.length) {
            // If the current element of arr is not the start of any piece, return false
            if (!map.containsKey(arr[i])) {
                return false;
            }

            // Get the piece corresponding to arr[i]
            int pieceIndex = map.get(arr[i]);
            int[] piece = pieces[pieceIndex];

            // Verify that all elements of the piece match the subarray in arr
            for (int val : piece) {
                if (arr[i] != val) {
                    return false;
                }
                i++;
            }
        }

        return true;
    }
}