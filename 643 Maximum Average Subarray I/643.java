class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n < k) {
            return 0;
        }
        
        long sum = 0;
        for (int i = 0; i < k ; ++i) {
            sum += nums[i];
        }
        long maxSum = sum;
        for (int i = k; i < n; ++i) {
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum * 1.0 / k;
    }

    // Time = O(n)
    // Space = o(1)
}