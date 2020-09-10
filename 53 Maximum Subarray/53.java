class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int preSum = 0;
        int globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            preSum = Math.max(preSum + nums[i], nums[i]);
            globalMax = Math.max(globalMax, preSum);
        }
        return globalMax;
    }
    // Time = O(n)
    // Space = O(1)
}