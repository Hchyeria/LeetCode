class Solution {
    // recursion
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        int m = s1.length();
        int n = s2.length();
        if (m != n) {
            return false;
        }
        return helper(s1, 0, s2, 0, m);
    }

    private boolean helper(String s1, int s1L, String s2, int s2L, int len) {
        if (len <= 0) {
            return false;
        }
        if (len == 1) {
            return s1.charAt(s1L) == s2.charAt(s2L);

        }
        int[] count = new int[26];
        // for (int i = s1L; i < s1L + len; ++i) excuse me???
        for (int i = 0; i < len; ++i) {
            count[s1.charAt(s1L + i) - 'a']++;
            count[s2.charAt(s2L + i) - 'a']--;
        }
        for (int c : count) {
            if (c <  0) {
                return false;
            }
        }


        for (int i = 1; i < len; ++i) {
            if (helper(s1, s1L, s2, s2L, i)
                    && helper(s1, s1L + i, s2, s2L + i, len - i)) {
                return true;
            }
            if (helper(s1, s1L, s2, s2L + len - i, i)
                    && helper(s1, s1L + i, s2, s2L, len - i)) {
                return true;
            }
        }

        return false;
    }
    // Time = O(4^n)
    // Space = O(n)

    // dp
    public boolean isScramble2(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        int m = s1.length();
        int n = s2.length();
        if (m != n) {
            return false;
        }

        boolean[][][] dp = new boolean[m][m][m + 1];
        // dp[i][j][k] = dp[i][j][o] and dp[i + o][j + o][k - o]
        //                  or dp[i][j + k - o][o] and dp[i + o][j][k - o]
        for (int len = 1; len <= m; ++len) {
            for (int i = 0; i <= m - len; ++i) {
                for (int j = 0; j <= m - len; ++j) {
                    if (len == 1) {
                        dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
                    }

                    for (int k = 1; k < len; ++k) {
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }

            }
        }
        return dp[0][0][m];

    }
    // Time = O(n^4)
    // Space = O(n^3)
    
}