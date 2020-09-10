class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        
        int n = nums.length;
        long cur = 01L;
        int left = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            cur *= nums[i];
            // need to make sure left < n
            while (cur >= k && left < n) {
                cur /= nums[left++];
            }
            // need to make sure i - left + 1 >= 1
            // left maybe exceed i by one
            if (i >= left) {
                res += i - left + 1;
            }
        }
        return res;
    }

    // Time = O(n)
    // Space = O(1)
}