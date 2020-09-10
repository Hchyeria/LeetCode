class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[left]) {
                left ++;
            } else if (nums[mid] == nums[n - 1]) {
                right--;
            } else if (nums[mid] > nums[0] && nums[mid] > nums[n - 1] ) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (nums[left] <= nums[right]) {
            return nums[left];
        } else {
            return nums[right];        
        }
        
    }
    
    // Time: O(log(n)), worse case O(n), reduce one element each time
    // Space: O(1)
}