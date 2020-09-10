class Solution {
    public int evalRPN(String[] tokens) {
       if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        for (String s : tokens) {
            int len = s.length();
            if (len == 1 && !Character.isDigit(s.charAt(0))) {
                int right = stack.pollFirst();
                int left = stack.pollFirst();
                if (s.equals("+")) {
                    stack.offerFirst(left + right);
                } else if (s.equals("-")) {
                    stack.offerFirst(left - right);
                } else if (s.equals("*")) {
                    stack.offerFirst(left * right);
                } else if (s.equals("/")) {
                    stack.offerFirst(left / right);
                }
                continue;
            }
            // be care about ["3","-4","+"], "-4" case
            // int num = 0;
            // for (int j = 0; j < len; ++j) {
            //     num = 10 * num + (s.charAt(j) - '0');
            // }
            stack.offerFirst(Integer.parseInt(s));
        }

        return stack.pollFirst();
    }
    // Time = O(n)
    // Space = O(n)
}