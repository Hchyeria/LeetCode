class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int left = 0;
        int sum = 0;
        // res initialized as Integer.MAX_VALUE or n + 1
        // can not be n, because the result maybe n
        int res = n + 1;
        for (int i = 0; i < n; ++i)  {
            sum += nums[i];
            
            while (sum >= s) {
                res = Math.min(res, i - left + 1);
                sum -= nums[left++];
            }
        }
        // remember to judge res == n + 1
        return res == n + 1 ? 0 : res;
    }
    // Time = O(n)
    // Space = O(1)
}