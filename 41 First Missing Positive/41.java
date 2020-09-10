class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        
        int n = nums.length;
        
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while ((v = nums[i]) >= 1 && v <= n && nums[v - 1] != v) {
                swap(nums, v - 1, i);
            }
        }
        
        int res = n + 1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                res = i + 1;
                break;
            }
        }
        return res;
    }

    // Time = O(n * swap)
    // Space = O(1)
    
    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}