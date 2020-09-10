class Solution {
    // dp
    // boolean dp[i][j]: represents whether this first i size of s anf j size of p match
    // Initial:
    //         dp[0][0] = true, dp[0][j] = if p only contains '*'
    //         dp[i][0] = false
    // Induction rule:
    //         dp[i][j] = dp[i-1][j-1], is s[i] == p[j] or p[j] == '?'
    //         if p[j] = '*'
    //              Case 1: dp[i][j] = dp[i-1][j], '*' represent letter
    //              Case 2: dp[i][j] = dp[i][j-1], '*' represent empty

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) != '*') {
                break;
            } else {
                dp[0][i] = true;
            }
        }
        
        for (int i = 1; i<= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                 } else if (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n)
}