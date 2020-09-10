class Solution {

    // Solution 1: DP
    // Bad
    public int wiggleMaxLength(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int n = nums.length;
        int[] dp = new int[n];
        int maxVal = 1;
        dp[0] = 1;
        dp[1] = 1;
        if (nums[1] != nums[0]) {
            if (nums[1] > nums[0]) {
                dp[1] = 2;
            } else {
                dp[1] = -2;
            }
            maxVal = 2;
        }
        for (int i = 2; i < n; ++i) {
            for (int j = i - 1; j >= 1 && j > Math.abs(dp[i]) - 2; --j) {
                int now = nums[i] - nums[j];
                int pre = dp[j];
                if ((now > 0 && pre < 0) || (now < 0 && pre > 0)) {
                    int len = Math.abs(pre) + 1;
                    if (len > Math.abs(dp[i])) {
                        dp[i] = now > 0 ? len : -len;
                    }
                } else if (nums[i] != nums[i - 1]) {
                    if (Math.abs(dp[i]) < 2) {
                        dp[i] = nums[i] > nums[i - 1] ? 2 : -2;
                    }
                }
                maxVal = Math.max(maxVal, Math.abs(dp[i]));
            }
        }
        return maxVal;
    }
    // Time = O(n^2)
    // Space = O(n)

    // Solution 2: DP
    // Good
    public int wiggleMaxLength2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] up = new int[n];
        int[] down = new int[n];
        
        up[0] = 1;
        down[0] = 1;
        
        for (int i = 1; i < n; ++i) {
            int now = nums[i] - nums[i - 1];
            if (now > 0) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (now < 0) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    // Time = o(n)
    // Space = O(n) -> o(1)

    // optimise space
    // greedy
    public int wiggleMaxLength3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int up = 1, down = 1;
        
        for (int i = 1; i < n; ++i) {
            int now = nums[i] - nums[i - 1];
            if (now > 0) {
                up = down + 1;
            } else if (now < 0) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }


}