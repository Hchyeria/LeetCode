class Solution {
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        for (int i = 0; i <= num; ++i) {
            dp[i] = (i & 1) + dp[i >>> 1];
        }
        return dp;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1), dp[] is the result
}