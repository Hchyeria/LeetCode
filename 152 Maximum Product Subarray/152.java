class Solution {
    public int maxProduct(int[] nums) {
        int preMax = 1, preMin = 1;
        int curMax = 0, curMin = 0;
        int globalMax = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i < 0) {
                curMax = Math.max(i, preMin * i);
                curMin = Math.min(i, preMax * i);
            } else {
                curMax = Math.max(i, preMax * i);
                curMin = Math.min(i, preMin * i);
            }
            preMax = curMax;
            preMin = curMin;
            globalMax = Math.max(globalMax, preMax);
        }
        return globalMax;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}