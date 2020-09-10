class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        // dp[0] should be 1, not 0
        dp[0] = 1;
        
        for (int i = 1; i <= n; ++i) {
            char c = s.charAt(i - 1);
            if ('0' < c && c <= '9') {
                dp[i] += dp[i - 1];
            }
            if (i >= 2) {
                int twoDigit = (10 * (s.charAt(i - 2) - '0')) + (s.charAt(i - 1) - '0');
                if (10 <= twoDigit && twoDigit <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
            
        }
        return dp[n];
    }
    // Time = O(n)
    // Space = O(n)

    public int numDecodings2(String s) {
        int n = s.length();
        int pre = 1, prepre = 0, now = 0;    
        for (int i = 1; i <= n; ++i) {
            now = 0;
            char c = s.charAt(i - 1);
            if ('0' < c && c <= '9') {
                now += pre;
            }
            if (i >= 2) {
                int twoDigit = (10 * (s.charAt(i - 2) - '0')) + (s.charAt(i - 1) - '0');
                if (10 <= twoDigit && twoDigit <= 26) {
                    now += prepre;
                }
            }
            prepre = pre;
            pre = now;
            
        }
        return now;
    }
    // Time = O(n)
    // Space = O(1)
}