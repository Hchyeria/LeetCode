class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int l = mid == 0 ? Integer.MIN_VALUE : nums[mid - 1];
            int r = mid == n - 1 ? Integer.MIN_VALUE : nums[mid + 1];
            if (nums[mid] > l && nums[mid] > r) {
                return mid;
            } else if (nums[mid] < l) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // Time = O(log(n))
    // Space = O(1)
}