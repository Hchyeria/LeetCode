class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        
        for (int i = 2; i <= n; ++i) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], 
                            dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    // Time = O(n)
    // Space = O(n)

    // optimise space
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int pre = 0, pre2 = 0;
        int res = 0;
        for (int i = 2; i <= n; ++i) {
            res = pre + cost[i - 1];
            res = Math.min(res, pre2 + cost[i - 2]);
            pre2 = pre;
            pre = res;
        }
        return res;
    }

    // Time = O(n)
    // Space = O(1)
}