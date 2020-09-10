// 515. Paint House
// LintCode

public class Solution {
    /**
     * @param costs: n x 3 cost matrix
     * @return: An integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        // write your code here
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int[][] dp = new int[2][3];
        
        int old, now = 0;
        for (int i = 0; i < n; ++i) {
            old = now;
            now = 1 - now;
            for (int j = 0; j < 3; ++j) {
                dp[now][j] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; ++k) {
                    if (k != j) {
                        dp[now][j] = Math.min(dp[now][j], dp[old][k] + costs[i][j]);
                    }
                }
            }
        }
        return Math.min(dp[now][0], Math.min(dp[now][1], dp[now][2]));
        
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // you should consider the initial condition, when i == 0
}