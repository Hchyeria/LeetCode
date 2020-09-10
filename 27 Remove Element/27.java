class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] == val) {
                fast++;
            } else{
                nums[slow++] = nums[fast++];
            }
        }
        return slow;
    }
    
    // Time complexity: O(n)
    // Space complexity: O(1)
}