class Solution {
    // Solution 1: DP
    // dp[i]: represents whether I can jump from the i-th element to the target
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; --i) {
            if (i + nums[i] >= nums.length) {
                dp[i] = true;
            } else {
                boolean flag = false;
                for (int j = i; j <= i + nums[i]; ++j) {
                    if (dp[j]) {
                        flag = true;
                        break;
                    }
                }
                dp[i] = flag;
            }
        }
        return dp[0];
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(n)

    // Solution 2: greed
    public boolean canJump2(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int curMax = 0;
        for (int i = 0; i < nums.length; ++i) {
            curMax = i <= curMax ? Math.max(curMax, i + nums[i]) : curMax;
        }
        return curMax >= nums.length - 1;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    public boolean canJump3(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        
        int n = nums.length;
        int maxDistance = 0;
        for (int i = 0; i <= maxDistance; ++i) {
            maxDistance = Math.max(maxDistance, i + nums[i]);
            if (maxDistance >= n - 1) {
                return true;
            }
        }
        return false;
    }
}