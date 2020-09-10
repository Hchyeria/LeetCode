public class Solution {
    /**
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        int n = nums.length;
        double minVal = Double.POSITIVE_INFINITY, maxVal = Double.NEGATIVE_INFINITY;
        for (int i : nums) {
            minVal = Math.min(minVal, i);
            maxVal = Math.max(maxVal, i);
        }
        
        double exp = 1e-5;
        
        while (maxVal - minVal > exp) {
            double mid = minVal + (maxVal - minVal) / 2.0;
            if (isValid(nums, mid, k)) {
                minVal = mid;
            } else {
                maxVal = mid;
            }
        }
        return minVal;
    }
    
    private boolean isValid(int[] nums, double val, int k) {
        int i = 0;
        double sum = 0;
        while (i < k) {
            sum += nums[i++] - val;
        }
        if (sum >= 0) {
            return true;
        }
        double pre = 0L;
        double minVal = 0L;
        int n = nums.length;
        while (i < n) {
            sum += nums[i] - val;
            pre += nums[i - k] - val;
            minVal = Math.min(minVal, pre);
            if (sum - minVal >= 0) {
                return true;
            }
            i++;
        }
        return false;
    }
    
    // Time = O(n * log(max - min))
    // Space = O(1)
}