class Solution {
    
    // dp[i][j]: represent the minimum sum of a falling path at [i, j]
    // dp[i][j] = min(dp[i - 1][k]) + A[i][j], for k in range [j-1, j + 1]
    public int minFallingPathSum(int[][] A) {
        int n = A.length;
        if (n == 1) {
            return A[0][0];
        }
        int[][] dp =new int[n][n];
        for (int i = 0; i < n; ++i) {
            dp[0][i] = A[0][i];
        }
        
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = Integer.MAX_VALUE;
                for (int k = j - 1; k <= j + 1; ++k) {
                    if (k >= 0 && k < n) {
                        temp = Math.min(temp, dp[i - 1][k]);
                    }
                }
                dp[i][j] = temp + A[i][j];
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            res = Math.min(res, dp[n - 1][i]);
        }
        return res;
    }

    // Time = O(n ^ 2)
    // Space = O(n ^ 2)

    // optimise space 
    public int minFallingPathSum2(int[][] A) {
        int n = A.length;
        if (n == 1) {
            return A[0][0];
        }
        int[][] dp =new int[2][n];
        int now = 0, old = 1;
        for (int i = 0; i < n; ++i) {
            dp[now][i] = A[0][i];
        }
        
        for (int i = 1; i < n; ++i) {
            old = now;
            now = 1 - now;
            for (int j = 0; j < n; ++j) {
                int temp = Integer.MAX_VALUE;
                for (int k = j - 1; k <= j + 1; ++k) {
                    if (k >= 0 && k < n) {
                        temp = Math.min(temp, dp[old][k]);
                    }
                }
                dp[now][j] = temp + A[i][j];
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            res = Math.min(res, dp[now][i]);
        }
        return res;
    }
}