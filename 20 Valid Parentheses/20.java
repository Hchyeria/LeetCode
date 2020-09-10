class Solution {
    private static final char[] datas = { '{', '}', '[', ']', '(', ')' };
    public boolean isValid(String s) {
        if (s == null || s.length() == 1) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        Map<Character, Integer> map = new HashMap<>();
        int index = 0;
        for (char c : datas) {
            map.put(c, index++);
        }

        Deque<Integer> stack = new LinkedList<>();
        int n = s.length();
        Integer num = 0;
        for (int i = 0; i < n; ++i) {
            num = map.get(s.charAt(i));
            if (num == null) {
                return false;
            }
            if (num % 2 == 1) {
                if (stack.isEmpty() || (stack.pollFirst() ^ num) != 1) {
                    return false;
                }
            } else {
                stack.offerFirst(num);
            }
        }
        //

        // need to check whether stack is empty !!
        // when input is "((", after loop the stack is not empty, so we should return false
        return stack.isEmpty();
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
    
    public boolean isValid2(String s) {
        if (s == null || s.length() == 1) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }

        Deque<Character> stack = new LinkedList<>();
        int n = s.length();
 
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '{') {
                stack.offerFirst('}');
            } else if (c == '[') {
                stack.offerFirst(']');
            } else if (c == '(') {
                stack.offerFirst(')');
            } else {
                // need to ensure stack is not empty then we can poll
                if (stack.isEmpty() || stack.pollFirst() != c) {
                    return false;
                }
            }
        }
        // need to check whether stack is empty !!
        // when input is "((", after loop the stack is not empty, so we should return false
        return stack.isEmpty();
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
}