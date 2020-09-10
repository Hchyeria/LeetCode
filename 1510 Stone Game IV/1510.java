class Solution {
    public boolean winnerSquareGame(int n) {
        if (n == 1) {
            return true;
        }
        
        boolean[] dp = new boolean[n + 1];
        dp[1] = true;
        dp[0] = false;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j * j <= i; ++j) {
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    // Time = O(n)
    // Space = O(n)
}