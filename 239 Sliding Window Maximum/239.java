class Solution {
    // Solution 1: maxHeap, lazy delete
    // Time = O(n*log(n)), Space = O(n)

    // Solution 2: Deque
    // Time = O(n), Space = O(n)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0 || nums == null || nums.length == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int right = 0; right < nums.length; ++right) {
            while (!deque.isEmpty() && deque.peekFirst() < right - k + 1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[right]) {
                deque.pollLast();
            }
            deque.offerLast(right);
            if (right >= k - 1) {
                res[right - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}