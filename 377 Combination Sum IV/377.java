class Solution {
    // dp
    // dp[i] = dp[i - A[0]] + dp[i - A[1]] ... + dp[i - A[n]], A[0..n] <= i
    // can't pick the first number then pick second number
    // because the order is not matter 
    // (1, 1, 2), (1, 2, 1) regarded as different result not one
    // so we should try each number at each loop
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
         
        for (int i = 1; i <= target; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    // Time = O(n * target)
    // Space = O(target)
}