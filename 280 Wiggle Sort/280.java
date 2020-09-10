// LintCode
// 508. Wiggle Sort

public class Solution {
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        // write your code here
        if (nums == null || nums.length <= 1) {
            return;
        }
        int n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (((i % 2 == 1) && nums[i] < nums[i - 1])
                || ((i % 2 == 0) && nums[i] > nums[i - 1])) {
                    swap(nums, i - 1, i);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Time = O(n)
    // Space = O(1)
}