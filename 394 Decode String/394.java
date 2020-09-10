class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        Deque<Integer> countStack = new LinkedList<>();
        Deque<StringBuilder> sequenceStack = new LinkedList<>();
        int fast = 0;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (fast < s.length()) {
            if (Character.isDigit(s.charAt(fast))) {
                // not the count += 10 * count + (s.charAt(fast) - '0'); !!!
                count = 10 * count + (s.charAt(fast) - '0');
            } else if (s.charAt(fast) == '[') {
                // we must create a new StringBuilder(sb)
                // otherwise the changes of sb will also lead to the change of it in the stack
                // if we change the sb to String
                // and string += otherString will not affect
                sequenceStack.offerFirst(new StringBuilder(sb));
                countStack.offerFirst(count);
                count = 0;
                sb.setLength(0);
            } else if (s.charAt(fast) == ']') {
                StringBuilder temp = new StringBuilder(sequenceStack.pollFirst());
                int n = countStack.pollFirst();
                while (n-- > 0) {
                    temp.append(sb);
                }
                sb = temp;
            } else {
                sb.append(s.charAt(fast));
            }
            fast++;
        }
        return sb.toString();
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)


    public String decodeString2(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        Deque<Integer> nums = new LinkedList<>();
        Deque<StringBuilder> sbs = new LinkedList<>();
        int num = 0;
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                sbs.offerFirst(sb);
                nums.offerFirst(num);
                num = 0;
                sb = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = sbs.pollFirst();
                int count = nums.pollFirst();
                for (int j = 0; j < count; ++j) {
                    tmp.append(sb);
                }
                sb = tmp;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}