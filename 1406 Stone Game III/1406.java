class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] preSum = new int[n + 1];
        
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + stoneValue[i];
        }
        
        if (n <= 3 && preSum[n] > 0) {
            return "Alice";
        }
        
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            dp[i] = Integer.MIN_VALUE;
            for (int j = 1; j <= 3 && i + j <= n; ++j) {
                int take = preSum[i + j] - preSum[i];
                dp[i] = Math.max(dp[i], take - (i + j == n ? 0 : dp[i + j]));
            }
        }
        if (dp[0] == 0) {
            return "Tie";
        }
        return dp[0] > 0 ? "Alice" : "Bob";
    }

    // Time = O(n)
    // Space = O(n)
}