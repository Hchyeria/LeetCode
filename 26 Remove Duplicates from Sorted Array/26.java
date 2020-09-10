class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums.length;
        }
        int slow = 1, fast = 1;
        while (fast < nums.length) {
            if (slow >= 1 && nums[fast] == nums[slow - 1]) {
                fast++;
            } else {
                nums[slow++] = nums[fast++];
            }
        }
        return slow;
    }

    // Time complexity: O(n)
    // Space complexity: O(1)
}