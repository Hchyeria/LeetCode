// 91. Minimum Adjustment Cost
// LintCode

public class Solution {
    /*
     * @param A: An integer array
     * @param target: An integer
     * @return: An integer
     */
    public int MinAdjustmentCost(List<Integer> A, int target) {
        // write your code here
        if (A == null || A.size() == 0) {
            return 0;
        }
        if (target >= 100) {
            return 0;
        }
        int n = A.size();
        int[][] dp = new int[n][100 + 1];
        
        for (int i = 0; i <= 100; ++i) {
            dp[0][i] = Math.abs(A.get(0) - i);
        }
        
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= 100; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
                // for (int k = j - target; k >= 0 && k <= 100 && k <= j + target; ++k)
                // is different from the below code
                for (int k = j - target; k <= j + target; ++k) {
                    if (k < 0 || k > 100) {
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k]);
                }
                dp[i][j] += Math.abs(A.get(i) - j);
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= 100; ++i) {
            res = Math.min(res, dp[n - 1][i]);
        }
        return res;
    }

    // Time = O(n * 100 * target)
    // Space = O(n * 100) -> optimise to O(100)

    // optimise space
    public int MinAdjustmentCost2(List<Integer> A, int target) {
        // write your code here
        if (A == null || A.size() == 0) {
            return 0;
        }
        if (target >= 100) {
            return 0;
        }
        int n = A.size();
        int[][] dp = new int[2][100 + 1];
        
        int old = 0, now = 0;
        
        for (int i = 0; i <= 100; ++i) {
            dp[now][i] = Math.abs(A.get(0) - i);
        }
        
        for (int i = 1; i < n; ++i) {
            old = now;
            now = 1 - now;
            for (int j = 0; j <= 100; ++j) {

                dp[now][j] = Integer.MAX_VALUE;

                for (int k = j - target; k <= j + target; ++k) {
                    if (k < 0 || k > 100) {
                        continue;
                    }
                    dp[now][j] = Math.min(dp[now][j], dp[old][k]);
                }
                dp[now][j] += Math.abs(A.get(i) - j);
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= 100; ++i) {
            res = Math.min(res, dp[now][i]);
        }
        return res;
        
    }
}