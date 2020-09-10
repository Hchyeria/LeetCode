class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        int n = nums.length;

        int sum = 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, -1);
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            sum = k == 0 ? sum : sum % k;
            Integer p = prefixSum.get(sum);
            if (p != null && i - p >= 2) {
                return true;
            }
            if (p == null) {
                prefixSum.put(sum, i);
            }
        }
        return false;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
}