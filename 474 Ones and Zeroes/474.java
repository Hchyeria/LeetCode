class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int k = strs.length;
        int[][][] dp = new int[k + 1][m + 1][n + 1];
        for (int i = 1; i <= k; ++i) {
            int ones = 0, zeros = 0;
            String s = strs[i - 1];
            int len = s.length();
            for (int p = 0; p < len; ++p) {
                if (s.charAt(p) == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
            
            // j in range [0, m], not [1, m]
            // o in range [0, n], not [1, n]
            for (int j = 0; j <= m; ++j) {
                for (int o = 0; o <= n; ++o) {
                    dp[i][j][o] = dp[i - 1][j][o];
                    if (j >= zeros && o >= ones) {
                        dp[i][j][o] = Math.max(dp[i][j][o], dp[i - 1][j - zeros][o - ones] + 1);
                    }
                }
            }
        }
        return dp[k][m][n];
    }
    // Time = O(m * n * k)
    // Space = O(m * n * k) -> optimise to O(m * n)

    // optimise space
    public int findMaxForm2(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int k = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= k; ++i) {
            int ones = 0, zeros = 0;
            String s = strs[i - 1];
            int len = s.length();
            for (int p = 0; p < len; ++p) {
                if (s.charAt(p) == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
            
            for (int j = m; j >= 0; --j) {
                for (int o = n; o >= 0; --o) {
                    if (j >= zeros && o >= ones) {
                        dp[j][o] = Math.max(dp[j][o], dp[j - zeros][o - ones] + 1);
                    }
                }
            }
        }
        return dp[m][n];
        
    }
}