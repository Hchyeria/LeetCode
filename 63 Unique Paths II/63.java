class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[2][n];
        int old, now = 0;
        for (int i = 0; i < m; ++i) {
            old = now;
            now = 1 - now;
            
            for (int j = 0; j < n; ++j) {
                dp[now][j] = 0;
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[now][j] = 1;
                    continue;
                }
                
                if (i > 0) {
                    dp[now][j] += dp[old][j];
                }
                if (j > 0) {
                    dp[now][j] += dp[now][j - 1];
                }
            }
        }
        return dp[now][n - 1];
    }
    // Time Complexity: O(m * n)
    // Space Complexity: O(n)

    // 1D Array
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[j] = 1;
                    continue;
                }
                int temp = 0;
                if (i > 0) {
                    temp += dp[j];
                }
                if (j > 0) {
                    temp += dp[j - 1];
                }
                dp[j] = temp;
            }
        }
        return dp[n - 1];
    }
}