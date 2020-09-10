class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        // len == 1
        for (int i = 0; i <= n - 1; ++i) {
            dp[i][i] = 1;
        }
        
        // len == 2
        for (int i = 0; i <= n - 2; ++i) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1;
        }

        // for (int len = 3; len <= n; ++len) {
        //     for (int i = 0; i <= n - len; ++i) {
        //         int j = i + len - 1;
        //         if (s.charAt(i) == s.charAt(j)) {
        //             dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
        //         } else {
        //             dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        //         }
        //     }
        // }
        
        // this method is faster than the above code
        // because of the principle of locality
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 2; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
    // Time = O(n^2)
    // Space = O(n ^ 2)

    // print the solution
    public int longestPalindromeSubseqPrint(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        int[][] pi = new int[n][n];
        // len == 1
        for (int i = 0; i <= n - 1; ++i) {
            dp[i][i] = 1;
        }
        
        // len == 2
        for (int i = 0; i <= n - 2; ++i) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1;
        }
        
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 2; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                    if (dp[i][j] == dp[i + 1][j - 1] + 2) {
                        pi[i][j] = 0;
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    if (dp[i][j] == dp[i + 1][j]) {
                        pi[i][j] = 1;
                    } else {
                        pi[i][j] = 2;
                    }
                }
            }
        }
        char[] res = new char[dp[0][n - 1]];
        int l = 0, r = n - 1, i = 0, j = dp[0][n - 1] - 1;
        while (l <= r) {
            if (l == r) {
                res[i] = s.charAt(l);
                break;
            }
            if (l + 1 == r) {
                res[i] = s.charAt(l);
                res[j] = s.charAt(r);
                break;
            }
            if (pi[l][r] == 0) {
                res[i++] = s.charAt(l++);
                res[j--] = s.charAt(r--);
            } else if (pi[l][r] == 1) {
                l++;
            } else {
                r--;
            }
        }
        System.out.println(new String(res));
        return dp[0][n - 1];
        
    }
}