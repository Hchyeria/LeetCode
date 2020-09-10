class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        if (n == 1) {
            return 0;
        }
        
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; ++i) {            
            preSum[i + 1] = preSum[i] + stoneValue[i];
        }
        
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = i; k < j; ++k) {
                    int left = preSum[k + 1] - preSum[i];
                    int right = preSum[j + 1] - preSum[k + 1];
                    if (left > right) {
                        dp[i][j] = Math.max(dp[i][j], dp[k + 1][j] + right);
                    } else if (left < right) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + left);
                    } else {
                        dp[i][j] = Math.max(dp[i][j], 
                                            Math.max(dp[k + 1][j] + right, dp[i][k] + left));
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    // Time = O(n^3)
    // Space = O(n^2)
}