class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        int n = heights.length;
        int globalMax = Integer.MIN_VALUE;
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i <= n; ++i) {
            while (!stack.isEmpty() && (i == n || heights[stack.peekFirst()] > heights[i])) {
                int item = stack.pollFirst();
                int left = stack.peekFirst() == null ? 0 : stack.peekFirst() + 1;
                globalMax = Math.max(globalMax, (i - left) * heights[item]);
            }
            stack.offerFirst(i);
        }
        return globalMax;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
}