class Solution {
    
    // dp
    // dp[i][j] = s[i] == s[j] && dp[i + 1][j - 1]
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }
        char[] input = s.toCharArray();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int left = 0, right = 0;
        for (int i = 0; i < n; ++i) {
            dp[i][i] = true;
            if (i < n - 1) {
                if (input[i] == input[i + 1]) {
                    dp[i][i + 1] = true;
                    left = i;
                    right = i + 1;
                }
            }
        }
        
        for (int i = n - 3; i >= 0; --i) {
            for (int j = i + 2; j < n; ++j) {
                if (input[i] == input[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                    if (dp[i][j] && j - i > right - left) {
                        left = i;
                        right = j;
                    }
                }
            }
        }
        
        return new String(input, left, right - left + 1);
    }

    // Time = O(n^2)
    // Space = O(n^2)
}