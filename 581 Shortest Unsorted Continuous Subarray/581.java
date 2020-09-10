class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int left = n - 1, right = 0;
        Deque<Integer> stack = new LinkedList<>();
        
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peekFirst()]) {
                left = Math.min(left, stack.pollFirst());
            }
            stack.offerFirst(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peekFirst()]) {
                right = Math.max(right, stack.pollFirst());
            }
            stack.offerFirst(i);
        }
        
        return right - left > 0 ? right - left + 1 : 0;
    }
    // Time = O(n)
    // Space = O(n)

    public int findUnsortedSubarray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int left = -1, right = -2;
        for (int i = 0; i < n; ++i) {
            min = Math.min(min, nums[n - 1 - i]);
            max = Math.max(max, nums[i]);
            
            if (nums[i] < max) {
                right = i;
            }
            if (nums[n - 1 - i] > min) {
                left = n - 1 - i;
            }
        }
        return right - left + 1;
    }
    // Time = O(n)
    // Space = O(1)
}