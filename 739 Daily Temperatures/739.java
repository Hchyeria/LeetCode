class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && T[stack.peekFirst()] < T[i]) {
                int cur = stack.pollFirst();
                res[cur] = i - cur;
            }
            stack.offerFirst(i);
        }
        return res;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(n)
}
