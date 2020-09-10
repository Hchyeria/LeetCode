class Solution {
    // dp
    // divide the state into five stage
    // no stock    first have   no stock    second have    no stock
    // __________|____________|___________|______________|______
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
          return 0;
        }
        
        int n = prices.length;
        int[][] dp = new int[n + 1][5 + 1];
        
        for (int i = 1; i <= n; ++i) {
            // state 1 3 5
            for (int j = 1; j <= 5; j += 2) {
                dp[i][j] = dp[i - 1][j];
                if (i > 1 && j > 1) {
                    dp[i][j] = Math.max(dp[i][j], 
                                        dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }
            
            // state 2 4
            for (int j = 2; j <= 4; j += 2) {
                dp[i][j] = dp[i - 1][j - 1];
                if (i > 1) {
                    dp[i][j] = Math.max(dp[i][j],
                                        dp[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }
            }
        }
        int sum = 0;
        for (int i = 1; i <= 5; i += 2) {
            sum = Math.max(sum, dp[n][i]);
        }

        return sum;
    }
    // Time = O(n)
    // Space = O(n)



    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
}