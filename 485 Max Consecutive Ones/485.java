class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0, right = 0;
        int result = 0;
        int count = 0;
        while (right < nums.length) {
            if (nums[right] == 1) {
                right++;
                if (count == 0) {
                    result = Math.max(result, right - left);
                }
            } else {
                count++;
                right++;
            }
            if (count > 0) {
                if (nums[left++] == 0) {
                    count--;
                }
            }
        }
        return result;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    // Solution 2:
    public int findMaxConsecutiveOnes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        int res = 0;
        for (int n : nums) {
            count = n == 1 ? count + 1 : 0;
            res = Math.max(res, count);
        }
        return res;
    }

    // same with
    public int findMaxConsecutiveOnes22(int[] nums) {
        int maxHere = 0, max = 0;
        for (int n : nums)
            max = Math.max(max, maxHere = n == 0 ? 0 : maxHere + 1);
        return max; 
    } 

    // Time Complexity: O(n)
    // Space Complexity: O(1)

    // Follow up: you are allowed to flip at most one 0.
	// LeetCode #487 (Maximum Consecutive Ones II).
}