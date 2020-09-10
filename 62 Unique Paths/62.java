class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    if (i > 0) {
                        dp[i][j] += dp[i - 1][j];
                    }
                    if (j > 0) {
                        dp[i][j] += dp[i][j - 1];
                    }
                }
            }
        }
        return dp[m - 1][n - 1];
    }
    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n)

    public int uniquePaths2(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }
        int[] dp = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 && j == 0) {
                    dp[j] = 1;
                } else {
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
        }
        return dp[n - 1];
    }
    // Time Complexity: O(m * n)
    // Space Complexity: O(n)
}