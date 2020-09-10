class Solution {
    public int stoneGameII(int[] piles) {
        if (piles == null || piles.length == 0) {
            return 0;
        }
        int n = piles.length;
        int[][] dp = new int[n][n];
        int[] preSum = new int[n + 1];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += piles[i];
            preSum[i + 1] = res;
        }
        res = helper(preSum, 0, 1, dp);
        return (preSum[n] + res) / 2;
    }

    private int helper(int[] preSum, int left, int M, int[][] dp) {
        int n = preSum.length - 1;
        if (left >= n) {
            return 0;
        }
        if (dp[left][M] != 0) {
            return dp[left][M];
        }
        if (2 * M >= n - left) {
            return preSum[n] - preSum[left];
        }
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= 2 * M && left + i <= n; ++i) {
            int take = preSum[left + i] - preSum[left];
            res = Math.max(res, take - helper(preSum, left + i, Math.max(i, M), dp));
        }
        dp[left][M] = res;
        return res;
    }
    // Time = O(n ^ 3)
    // Space = O(n ^ 2)

    public int stoneGameII2(int[] piles) {
        if (piles == null || piles.length == 0) {
            return 0;
        }
        int n = piles.length;
        int[][] dp = new int[n][n];
        int[] preSum = new int[n];
        int res = 0;
        for (int i = n - 1; i >= 0; --i) {
            res += piles[i];
            preSum[i] = res;
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 1; --j) {
                if (i + 2 * j >= n) {
                    dp[i][j] = preSum[i];
                    continue;
                }
                for (int k = 1; k <= 2 * j; ++k) {
                    dp[i][j] = Math.max(dp[i][j], preSum[i] - dp[i + k][Math.max(k, j)]);
                }
            }
        }
        return dp[0][1];
    }

    // Time = O(n ^ 3)
    // Space = O(n ^ 2)
}