class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }

        int slow = 0, fast = 0;

        while (fast != nums.length) {
            if (nums[fast] != 0) {
                swap(nums, fast++, slow++);
            } else {
                fast++;
            }
        }
        return;
    }

    private void swap(int[] array, int i, int j) {
        if (i == j) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

/*
  Time complexity O(n)
  Space complexity O(1)
*/