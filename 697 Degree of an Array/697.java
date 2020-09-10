class Solution {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        Map<Integer, Integer> count = new HashMap<>(), first = new HashMap<>();
        int n = nums.length;
        int degree = 0;
        int res = n;
        for (int i = 0; i < n; ++i) {
            first.putIfAbsent(nums[i], i);
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            
            int c = count.get(nums[i]);
            if (c > degree) {
                degree = c;
                res = n;
            }
            if (c == degree) {
                res = Math.min(res, i - first.get(nums[i]) + 1);
            }
            
        }
        return res;
    }

    // Time = O(n)
    // Space = O(n)
}