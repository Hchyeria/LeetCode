class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            dp[i] += dp[i - 1];
            if (i > 1) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    // Time = O(n)
    // Space = O(n)

    // optimise space
    public int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        }
        // need to ensure n > 1
        int pre = 1, pre2 = 1;
        int res = 0;
        for (int i = 2; i <= n; ++i) {
            res = pre;
            if (i > 1) {
                res += pre2;
            }
            pre2 = pre;
            pre = res;
        }
        return res;
    }
    // Time = O(n)
    // Space = O(1)
}