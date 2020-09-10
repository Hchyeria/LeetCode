class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }
        
        int i = 0;
        int cur = 0;
        int k = nums.length-1;

        while (cur <= k) {
            if (nums[cur] == 0) {
                swap(nums, i++, cur++);
            } else if (nums[cur] == 1) {
                cur++;
            } else {
                swap(nums, cur, k--);
            }
        }
    }
    

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

// Time complexity is O(n).
// Space complexity is O(1).