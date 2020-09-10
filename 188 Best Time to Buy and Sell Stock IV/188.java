class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) {
            return 0;
        }
        int n = prices.length;
        if (k > n / 2) return quickSolve(prices);
        int[][] dp = new int[2][2 * k + 2];
        int old = 0, now = 0;
        for (int i = 1; i <= n; ++i) {
            old = now;
            now = 1 - now;
            for (int j = 1; j <= 2 * k + 1; j += 2) {
                dp[now][j] = dp[old][j];
                if (i > 1 && j > 1) {
                    dp[now][j] = Math.max(dp[now][j],
                                       dp[old][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }
            
            for (int j = 2; j <= 2 * k; j += 2) {
                dp[now][j] = dp[old][j - 1];
                if (i > 1) {
                    dp[now][j] = Math.max(dp[now][j],
                                       dp[old][j] + prices[i - 1] - prices[i - 2]);
                }
            }
        }
        
        int res = 0;
        for (int i = 1; i <= 2 * k + 1; i += 2) {
            res = Math.max(res, dp[now][i]);
        }
        return res;
        
    }
    
    private int quickSolve(int[] prices) {
        int n = prices.length;
        int sum = 0;
        for (int i = 1; i < n; ++i) {
            if (prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }

    // Time = O(n * k)
    // Space = O(k)
}