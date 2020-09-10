class Solution {
    // Solution 1: DP
    // dp[i]: represents the minimum number of jumps from i-th element to the target
    // dp[i] = min(dp[j]) + 1, for j in range [i+1, i + nums[i]]
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; --i) {
            if (i + nums[i] >= n - 1) {
                dp[i] = 1;
                continue;
            }
            // int curMin = Integer.MAX_VALUE;
            int curMin = n;
            for (int j = i + 1; j <= i + nums[i] && j < n; ++j) {
                // take care about, dp[j] + 1, when dp[j] == Integer.MAX_VALUE will be overflow
                // so we can't initial curMin as Integer.MAX_VALUE
                curMin = Math.min(curMin, dp[j] + 1);
            }
            dp[i] = curMin;
        }
        return dp[0];
    }

    // Time Complexity: O(n^2) or O(n * k) k is the largest number of jumps one can make
    // Space Complexity: O(n)

    // Solution 2: greed
    public int jump2(int[] nums) {
        int n = nums.length;
        int jump = 0; // the minimum jumps
        int curEnd = 0, i = 0; // the current jump can reach range [i, curEnd]
        int farthestEnd = 0; // the range of [i, curEnd] can reach maximum index
        for ( ; i < n; ++i) {
            if (i > curEnd) {
                jump++;

                if (curEnd == farthestEnd) {
                    return -1;
                }
                curEnd = farthestEnd;
                if (curEnd >= n - 1) {
                    break;
                }
            }
            farthestEnd = Math.max(farthestEnd, i + nums[i]);
        }
        return jump;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}