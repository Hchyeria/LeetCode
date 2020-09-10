/**
Optimization1: 
- Optimise time:
- by draw dp array grid 
- dp[i - 1][j - k*A[i - 1]] + k * V[i - 1] actually equal to dp[i][j - A[i - 1]] + V[i - 1]
- so it's not necessary to loop k times
- Optimise Space:
- can compress to 1-D array
*/

public class Solution {
    // optimise time
    public int backPackIII(int[] A, int[] V, int m) {
        if (A == null || A.length == 0 || m == 0) {
            return 0;
        }

        int n = A.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (j - A[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[n][m];
    }

    // optimise space
    public int backPackIII2(int[] A, int[] V, int m) {
        if (A == null || A.length == 0 || m == 0) {
            return 0;
        }

        int n = A.length;
        int[] dp = new int[m + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (j - A[i - 1] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[m];
    }
}