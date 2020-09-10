class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int res = Math.max(houseRob(nums, 0, n - 2), houseRob(nums, 1, n - 1));
        return res;
        
    }
    
    private int houseRob(int[] nums, int left, int right) {
        int old = 0, now = 0;
        old = nums[left];
        // remember to check the bound
        // although the rob function has already check the length of 2
        // but the length - 1 array passed in houseRob function
        if (right - left == 0) {
            return old;
        }
        now = Math.max(nums[left], nums[left + 1]);
        int t = 0;
        for (int i = left + 2; i <= right; ++i) {
            t = Math.max(old + nums[i], now);
            old = now;
            now = t;
        }
        return now;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)
}