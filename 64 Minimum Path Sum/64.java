class Solution {
    // dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int old = 0, now = 0;
        int[][] dp = new int[2][n];
        
        for (int i = 0; i < m; ++i) {
            old = now;
            now = 1 - now;
            
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) {
                    dp[now][j] = grid[0][0];
                    continue;
                }
                dp[now][j] = 0;
                int res = Integer.MAX_VALUE;
                if (i > 0) {
                    res = Math.min(res, dp[old][j]);
                }
                
                if (j > 0) {
                    res = Math.min(res, dp[now][j - 1]);
                }
                dp[now][j] += res + grid[i][j];
            }
        }
        return dp[now][n - 1];
        
    }

    // Time Complexity: O(m * n)
    // Space Complexity: O(n)
}