class Solution {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        if (length == 1) return nums[0];
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; ++i) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    // Time = O(n)
    // Space = O(n)

    // optimise space
    public int rob2(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        if (length == 1) return nums[0];
        int old = 0, now = 0;
        old = nums[0];
        now = Math.max(old, nums[1]);
        int t = 0;
        for (int i = 2; i < length; ++i) {
            t = Math.max(old + nums[i], now);
            old = now;
            now = t;
        }
        return now;
    }
}