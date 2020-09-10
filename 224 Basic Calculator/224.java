class Solution {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        
        Deque<Integer> stack = new LinkedList<>();
        int sign = 1;
        int num = 0, res = 0;
        stack.push(1);
        
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = 10 * num + (c - '0');
            } else if (c == '+' || c == '-') {
                res += sign * num;
                sign = stack.peekFirst() * (c == '+' ? 1 : -1);
                num = 0;
            } else if (c == '(') {
                stack.offerFirst(sign);
            } else if (c == ')') {
                stack.pollFirst();
            }
        }
        
        res += sign * num;
        return res;
    }

    // Time = O(n)
    // Space = O(n)
}