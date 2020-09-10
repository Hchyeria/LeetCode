class Solution {
    // bad
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (i == j) {
                    dp[i][j] = piles[i];
                    continue;
                }
                if (i + 1 == j) {
                    dp[i][j] = Math.max(piles[i], piles[j]);
                    continue;
                }
                if (piles[i + 1] > piles[j] && i < n - 2) {
                    dp[i][j] = Math.max(dp[i][j], piles[i] + dp[i + 2][j]);
                } else {
                    dp[i][j] = Math.max(dp[i][j], piles[i] + dp[i + 1][j - 1]);
                }
                
                if (piles[j - 1] > piles[i]) {
                    dp[i][j] = Math.max(dp[i][j], piles[j] + dp[i][j - 2]);
                } else {
                    dp[i][j] = Math.max(dp[i][j], piles[j] + dp[i + 1][j - 1]);
                }
                
            }
        }
        
        int sum = 0;
        for (int i : piles) {
            sum += i;
        }
        return dp[0][n - 1] > (sum / 2);
    }

    // good
    public boolean stoneGame2(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; ++i) {
            dp[i][i] = piles[i];
        }
        
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        
        return dp[0][n - 1] > 0;
    }

    // optimise space to 1D array
    public boolean stoneGame3(int[] piles) {
        int n = piles.length;
        int[] dp = new int[n];

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (i == j) {
                    dp[j] = piles[j];
                    continue;
                }
                dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j - 1]);
            }
        }
        
        return dp[n - 1] > 0;
    }
}