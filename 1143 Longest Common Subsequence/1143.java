class Solution {
    // dp
    // dp[i][j]: represents the longest common subsequence of first i size of text1 and first j size of text2
    // induction rule:
    //              dp[i][j] = dp[i-1][j-1] + 1, if text1[i] == text2[j]
    //              dp[i][j] = max(dp[i-1][j], dp[i][j-1]), otherwise
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];

    }
    
    // Time Complexity: O(m*n)
    // Space Complexity: O(m*n) -> can optimise to O(min(m, n)) -> O(1)

    // print solution
    public int longestCommonSubsequencePrint(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        int[][] pi = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    pi[i][j] = 0;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    if (dp[i][j] == dp[i - 1][j]) {
                        pi[i][j] = 1;
                    } else {
                        pi[i][j] = 2;
                    }
                }
            }
        }
        
        char[] res = new char[dp[m][n]];
        int i = m;
        int j = n;
        int p = dp[m][n] - 1;
        while (p >= 0) {
            if (j == 0 || i == 0) {
                break;
            }
            if (pi[i][j] == 0) {
                res[p--] = text1.charAt(i - 1);
                i--;
                j--;
            } else if (pi[i][j] == 1) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println(new String(res));
        return dp[m][n];
        
    }
}