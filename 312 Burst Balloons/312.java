class Solution {
    // https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] A = new int[n + 2];
        for (int i = 0; i < n; ++i) {
            A[i + 1] = nums[i];
        }
        n += 2;
        A[0] = A[n - 1] = 1;
        
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 2; j < n; ++j) {
                for (int k = i + 1; k < j; ++k) {
                    dp[i][j] = Math.max(dp[i][j], 
                                       dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
                }
            }
        }
        return dp[0][n - 1];
    }
    // Time = O(n^3)
    // Space = O(n^2)
}