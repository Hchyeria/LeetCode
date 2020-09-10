class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        long[] dp = new long[n + 1];
        long mod = 1000000007;
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int c = getTimes(s.charAt(i - 1));
            dp[i] += dp[i - 1] * c;
            if (i > 1) {
                c =  getTimes2(s.charAt(i - 2), s.charAt(i - 1));
                dp[i] += dp[i - 2] * c;
            }
            dp[i] %= mod;
        }
        
        return (int) dp[n];
    }
    
    private int getTimes(char c) {
        if (c == '*') {
            return 9;
        }
        int code = c - '0';
        if (1 <= code && code <= 9) {
            return 1;
        }
        return 0;
    }
    
    private int getTimes2(char pre, char c) {
        int c1 = c - '0';
        int preCode = pre - '0';
        if (pre == '*' && c == '*') {
            return 15;
        } else if (pre == '*') {
            if (0 <= c1 && c1 <= 6) {
                return 2;
            } else {
                return 1;
            }
        } else if (c == '*') {
            if (preCode == 1) {
                return 9;
            } else if (preCode == 2) {
                return 6;
            }
        } else {
            int code = preCode * 10 + c1;
            if (10 <= code && code <= 26) {
                return 1;
            }
        }
        return 0;
    }
}