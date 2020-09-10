class Solution {
    public int change(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }
        
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[0][0] = 1;
        
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= amount; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] += dp[i][j - coins[i - 1]];    
                }
                
            }
        }
        return dp[n][amount];
        
    }
    // optimise space
    public int change2(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }
        
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= amount; ++j) {
                if (j - coins[i - 1] >= 0) {
                    dp[j] += dp[j - coins[i - 1]];    
                }
                
            }
        }
        return dp[amount];
        
    } 
    // Time = O(m * n)
    // Space = O(n)
}