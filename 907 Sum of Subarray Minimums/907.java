class Solution {
    public int sumSubarrayMins(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        long sum = 0L;
        Deque<Integer> stack = new LinkedList<>();
        int n = A.length;
        for (int i = 0; i <= n; ++i) {
            while (!stack.isEmpty() && (i == n || A[stack.peekFirst()] > A[i])) {
                int cur = stack.pollFirst();
                int left = stack.isEmpty() ? cur + 1 : cur - stack.peekFirst();
                int right = i - cur;
                sum += right * left * A[cur];
            }
            stack.offerFirst(i);
        }
        return (int) (sum % (1E9 + 7));
    }
    // Time = O(n)
    // Space = O(n)
}