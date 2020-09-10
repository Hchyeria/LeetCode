class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null) return;
        int len = nums.length;
        if (len <= 1) return;
        int index = -1;
        for (int i = len - 2; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            reverse(nums, 0, len - 1);
            return;
        }
        int j = len - 1;
        while (nums[index] >= nums[j]) {
            j--;
        }
        swap(nums, index, j);
        reverse(nums, index + 1, len - 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
    
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }
    // Time = O(n)
    // Space = O(1)
}