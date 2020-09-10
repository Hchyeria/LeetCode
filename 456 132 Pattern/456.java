class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return false;
        }
        Deque<Integer> stack = new LinkedList<>();
        int s3 = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = n - 1; i >= 0; --i) {
            if (nums[i] < s3) {
                return true;
            } else {
                while (!stack.isEmpty() && stack.peekFirst() < nums[i]) {
                    s3 = stack.pollFirst();
                }
            }
            stack.offerFirst(nums[i]);
        }
        return false;
    }

    // Time = O(n)
    // Space = O(n)
}
