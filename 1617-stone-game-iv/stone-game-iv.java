class Solution {
    public boolean winnerSquareGame(int n) {

        boolean[] dp = new boolean[n + 1];

        // dp[0] = false
        // No stones -> player cannot move -> loses.

        for (int i = 1; i <= n; i++) {

            for (int x = 1; x * x <= i; x++) {

                int remaining = i - x * x;

                // If we can move to a losing state,
                // current player wins.
                if (!dp[remaining]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}