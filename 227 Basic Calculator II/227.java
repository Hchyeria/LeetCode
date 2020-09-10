class Solution {
    // Solution 1: with stack    
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        
        char[] input = s.toCharArray();
        Deque<Integer> stack = new LinkedList<>();
        int n = s.length();
        int sum = 0;
        int num = 0;
        char sign = '+';
        for (int i = 0; i < n; ++i) {
            char c = input[i];
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }
            if (i == n - 1 || (!Character.isDigit(c) && c!=' ')) {
                switch (sign) {
                    case '+':
                        stack.offerFirst(num);
                        break;
                    case '-':
                        stack.offerFirst(-num);
                        break;
                    case '*':
                        stack.offerFirst(stack.pollFirst() * num);
                        break;
                    case '/':
                        stack.offerFirst(stack.pollFirst() / num);
                        break;
                }
                sign = c;
                num = 0;
            }
        }
        
        while (!stack.isEmpty()) {
            sum += stack.pollFirst();
        }

        return sum;
    }
    // Time = O(n)
    // Space = O(n)


    // Solution 2: without stack
    public int calculate2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        
        char[] input = s.toCharArray();
        int n = s.length();
        int sum = 0;
        int num = 0, tempSum = 0;
        char sign = '+';
        for (int i = 0; i < n; ++i) {
            char c = input[i];
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }
            if (i == n - 1 || (!Character.isDigit(c) && c!=' ')) {
                switch (sign) {
                    case '+':
                        sum += tempSum;
                        tempSum = num;
                        break;
                    case '-':
                        sum += tempSum;
                        tempSum = -num;
                        break;
                    case '*':
                        tempSum *= num;
                        break;
                    case '/':
                        tempSum /= num;
                        break;
                }
                sign = c;
                num = 0;
            }
        }
        sum += tempSum;
        return sum;
    }
    // Time = O(n)
    // Space = O(1)
}