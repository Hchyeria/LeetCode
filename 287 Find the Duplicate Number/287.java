class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int n = nums.length;
        int left = 1;
        int right = n - 1;
         
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isLess(nums, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean isLess(int[] nums, int val) {
        int count = 0;
        for (int i : nums) {
            if (i <= val) {
                count++;
            }
        }
        return count > val; 
    }

    // Time = O(n * log(n))
    // Space = O(1)

    public int findDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        int fast = 0;
        int slow = 0;
        
        while (fast < n && nums[fast] < n) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (fast == slow) {
                break;
            }
        }
        
        slow = 0;
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
    // Time = O(n)
    // Space = O(1)

}