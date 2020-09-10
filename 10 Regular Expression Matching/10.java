class Solution {
    // Solution 1: recursion
    // should begin at the end of string, wht?
    // because when we meet the i-th position , if it's '*'
    // we should look the j-1 element, you should know the j-1 status is
    // so will recursion to j-1
    private int m;
    private int n;
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        m = s.length();
        n = p.length();
        return helper(s, p, m - 1, n - 1);
    }
    
    private boolean helper(String s, String p, int sIndex, int pIndex) {
        // the index -1, represent the empty string
        // when p is "" and s not empty, return false
        // when s is "" and p[j] == '*', let the match pattern become empty and go on
        if (sIndex == -1 && pIndex == -1) {
            return true;
        } else if (pIndex <= -1) {
            return false;
        } else if (sIndex == -1 && p.charAt(pIndex) == '*') {
            return helper(s, p, sIndex, pIndex - 2);
        } else if (sIndex <= -1) { 
            // when sIndex is empty && p.charAt(pIndex) != '*'
            // when sIndex <= -1, we should go on to check s.charAt(sIndex)
            // we should guarantee index >= 0
            return false;
        }
        if (s.charAt(sIndex) == p.charAt(pIndex)) {
            return helper (s, p, sIndex - 1, pIndex - 1);
        } else if (p.charAt(pIndex) == '.') {
            return helper (s, p, sIndex - 1, pIndex - 1);
        } else if (p.charAt(pIndex) == '*') {
            if (p.charAt(pIndex - 1) != s.charAt(sIndex) && p.charAt(pIndex - 1) != '.') {
                return helper(s, p, sIndex, pIndex - 2);
            } else {
                return helper(s, p, sIndex - 1, pIndex - 1)
                        || helper(s, p, sIndex, pIndex - 2)
                        || helper(s, p, sIndex - 1, pIndex);
            }
        }
        return false;
    }

    // Time Complexity: O(3^(m + n)), worse case
    // Space Complexity: O(m + n)


    // Solution 2: dp
    // dp[i][j]: represent whether match at the first i size letter in s and first j size letter in p
    // initial:
    // dp[0][0] = true
    // dp[i][0] = false, when the patter is empty
    // dp[0][j] = true, if p[i] == '*' && dp[0][j - 2]
    //          = false, otherwise
    // induction rule:
    // Case 1: if s[i] == p[j], dp[i][j] = dp[i-1][j-1]
    // Case 2: if p[j] == '.', dp[i][j] = dp[i-1][j-1]
    // Case 3: if p[j] == '*'
    //            Case 3.1: if p[j - 1] != s[i] && p[j - 1] != '.'
    //                  Case 3.1.1 dp[i][j] = dp[i][j-2], as a empty string
    //            Case 3.2: otherwise
    //                  Case 3.2.1: dp[i][j] = dp[i-1][j-1], as a single letter
    //                  Case 3.2.2: dp[i][j] = dp[i][j-2], as a empty string
    //                  Case 3.2.3: dp[i][j] = dp[i-1][j], as a multiple letters
    // we use the previous data from left-up position
    // so we build dp array from left-top 
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length(), n = p.length();
        // also can use hash table to store i*n+j
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j < n + 1; ++j) {
            if (p.charAt(j - 1) == '*' && (j > 1 && dp[0][j - 2])) {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                if (p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') { // else { } is wrong!!
                    if (j > 1) {
                        // clear the condition
                        if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
                            dp[i][j] = dp[i][j - 2];
                        } else {
                            dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 2] || dp[i - 1][j];
                        } 
                    }
                    
                }
            }
        }

        return dp[m][n];
    }

    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n)
}