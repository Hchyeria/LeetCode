class Solution {
    // dp
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        
        int n = nums.length;
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; ++i) {
            dp[i][0] = true;
        }
    
        
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j <= target; ++j) {
                if (i == 0) {
                    dp[i][j] = nums[i] == target;
                    continue;
                }
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i] >= 0) {
                    dp[i][j] |= dp[i - 1][j - nums[i]];
                }
            }
        }
        
        return dp[n - 1][target];
    }

    // Time = O(n * target)
    // Space = O(n * target) -> O(target)

    // optimise space
    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        
        int n = nums.length;
        boolean[][] dp = new boolean[2][target + 1];
        int now = 1, old = 0;
        
        for (int i = 0; i < n; ++i) {
            old = now;
            now = 1 - now;
            for (int j = 0; j <= target; ++j) {
                if (j == 0) {
                    dp[now][j] = true;
                    continue;
                }
                if (i == 0) {
                    dp[now][j] = nums[i] == target;
                    continue;
                }
                dp[now][j] = dp[old][j];
                if (j - nums[i] >= 0) {
                    dp[now][j] |= dp[old][j - nums[i]];
                }
            }
        }
        
        return dp[now][target];
    }
}