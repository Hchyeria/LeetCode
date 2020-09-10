class Solution {
    // Solution 1: stack
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        int n = s.length();
        int globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.offerFirst(i);
            } else {
                if (stack.isEmpty() || s.charAt(stack.peekFirst()) != '(') {
                    stack.offerFirst(i);
                } else {
                    stack.pollFirst();
                }
            }
        }
        if (stack.isEmpty()) {
            return n;
        }
        int a = n;
        while (!stack.isEmpty()) {
            int b = stack.pollFirst();
            globalMax = Math.max(globalMax, a - b - 1);
            a = b;
        }

        // don't forget this step
        globalMax = Math.max(globalMax, a);
        return globalMax;
        
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)

    // Solution 2: dp
    // dp[i]: represents the longest valid parenthesis from 0 to i (include i)
    // dp[i] = 0, if s[i] == '('
    //         if s[i] == ')', right add so far < left add so far
    //              Case 1: 2 + dp[i - 1], if 2 + dp[i - 1] == n
    //              Case 2: Case1+ dp[i - Case1], if 2 + dp[i - 1] < n    
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        
        int n = s.length();
        int globalMax = 0;
        int[] dp = new int[n];
        int leftCount = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else if (leftCount > 0) {
                dp[i] = 2 + dp[i - 1];
                if (i > dp[i]) {
                    dp[i] += dp[i - dp[i]];
                }
                globalMax = Math.max(globalMax, dp[i]);
                leftCount--;
            }
            
        }
        return globalMax;
        
    }
}