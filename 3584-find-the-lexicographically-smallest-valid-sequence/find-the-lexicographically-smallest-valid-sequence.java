class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        /*
         * dp[i] = maximum number of characters from the END
         * of word2 that can be matched as a subsequence
         * inside word1[i...n-1].
         */
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {

            dp[i] = dp[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        /*
         * Greedily choose the smallest possible index.
         */
        while (i < n && j < m) {

            // Case 1: characters already match.
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;
                i++;

            } else {

                /*
                 * Use our one allowed mismatch here.
                 *
                 * After taking word1[i], we need to match
                 * word2[j+1 ... m-1] exactly.
                 *
                 * Number of characters needed:
                 * m - j - 1
                 */
                if (dp[i + 1] >= m - j - 1) {

                    ans[j] = i;
                    j++;
                    i++;

                    // We have used the one mismatch.
                    break;
                }

                i++;
            }
        }

        /*
         * If we haven't used the mismatch yet, the rest
         * must be matched exactly.
         */
        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}