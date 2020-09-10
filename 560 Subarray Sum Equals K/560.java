class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int sum = 0;
        int count = 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        for (int num : nums) {
            sum += num;
            Integer cur = prefixSum.get(sum - k);
            if (cur != null) {
                count += cur;
            }
            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
            
        }
        return count;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
}
