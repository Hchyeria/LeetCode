public class Solution {
    /**
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[][][] dp = new int[n + 1][k + 1][target + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int m = 0; m <= k; ++m) {
                for (int j = 0; j <= target; ++j) {
                    dp[i][m][j] = dp[i - 1][m][j];
                    if (j >= A[i - 1] && m > 0) {
                        dp[i][m][j] += dp[i - 1][m - 1][j - A[i - 1]];
                    }
                }
            }
        }
        
        return dp[n][k][target];
    }
    // Time = O(n * k * target)
    // Space = O(n * k )

    // optimise space
    public int kSum2(int[] A, int k, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[][] dp = new int[k + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int m = k; m >= 0; --m) {
                for (int j = target; j >= 0; --j) {
                    if (j >= A[i - 1] && m > 0) {
                        dp[m][j] += dp[m - 1][j - A[i - 1]];
                    }
                }
            }
        }
        
        return dp[k][target];
        
    }
}





















