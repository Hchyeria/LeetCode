class Solution {
    // https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/339959/One-Pass-O(N)-Time-and-Space
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        Deque<Integer> stack = new LinkedList<>();
        int sum = 0;
        for (int i = 0; i <= n; ++i) {
            int right = i == n ? Integer.MAX_VALUE : arr[i];
            while (!stack.isEmpty() && ((stack.peekFirst() < right) || i == n)) {
                int cur = stack.pollFirst();
                int left = stack.isEmpty() ? Integer.MAX_VALUE : stack.peekFirst();
                int pro = Math.min(left, right);
                sum += pro == Integer.MAX_VALUE ? 0 : pro * cur;
            }
            stack.offerFirst(right);
        }
        return sum;
    }
    // Time = O(n)
    // Space = O(n)

    public int mctFromLeafValues2(int[] arr) {
        int n = arr.length;
        Deque<Integer> stack = new LinkedList<>();
        stack.offerFirst(Integer.MAX_VALUE);
        int sum = 0;
        for (int i : arr) {
            while (!stack.isEmpty() && stack.peekFirst() < i) {
                int cur = stack.pollFirst();
                sum += Math.min(stack.peekFirst(), i) * cur;
            }
            stack.offerFirst(i);
        }
        
        while (stack.size() > 2) {
            sum += stack.pollFirst() * stack.peekFirst();
        }
        return sum;
    }
}