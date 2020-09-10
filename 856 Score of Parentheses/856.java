class Solution {
    // very bad idea
    public int scoreOfParentheses(String S) {
        char[] input = S.toCharArray();
        Deque<Integer> stack = new LinkedList<>();
        Deque<Integer> count = new LinkedList<>();
        int n = input.length;
        for (int i = 0; i < n; ++i) {
            char c = input[i];
            if (c == '(') {
                stack.offerFirst(i);
            } else {
                int j = stack.pollFirst();
                int sum = 0;
                int before = 0;
                if (i - j == 1) { 
                    if (j > 0 && input[j - 1] == ')' && !count.isEmpty()) {
                        sum = count.pollFirst();
                    }
                    count.offerFirst(sum + 1);
                } else {
                    sum = count.isEmpty() ? 0 : count.pollFirst();
                    if (j > 0 && input[j - 1] == ')' && !count.isEmpty()) {
                        before = count.pollFirst();
                        count.offerFirst(2 * sum + before);
                    } else {                        
                        count.offerFirst(2 * sum);
                    }
                    
                }
            }
        }
        return count.peekFirst();
    }

    public int scoreOfParentheses2(String S) {
        char[] input = S.toCharArray();
        Deque<Integer> stack = new LinkedList<>();
        Deque<Integer> count = new LinkedList<>(); // only store the previous value 
        int n = input.length;
        // use cur to record current sum is much simpler than the above solution, which store every sum in stack
        // will cause chaos when under different situation
        int cur = 0;
        for (int i = 0; i < n; ++i) {
            char c = input[i];
            if (c == '(') {
                stack.offerFirst(i);
                count.offerFirst(cur);
                cur = 0;
            } else {
                int j = stack.pollFirst();
                int sum = 0;
                
                if (!count.isEmpty()) {
                    sum = count.pollFirst();
                }
                if (i - j == 1) { 
                    cur = sum + 1;
                } else {
                    cur = sum + 2 * cur;
                }
            }
        }
        return cur;
    }

    public int scoreOfParentheses3(String S) {
        char[] input = S.toCharArray();
        
        Deque<Integer> stack = new LinkedList<>();
        int count = 0;
        
        for (char c : input) {
            if (c =='(') {
                stack.offerFirst(count);
                count = 0;
            } else {
                count = stack.pollFirst() + Math.max(2 * count, 1);
            }
        }
        return count;
    }
}