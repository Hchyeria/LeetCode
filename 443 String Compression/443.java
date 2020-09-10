class Solution {
    public int compress(char[] chars) {
        if (chars == null || chars.length <= 1) {
            return chars.length;
        }
        int fast =0, slow = 0;
        while (fast < chars.length) {
            int pre = fast;
            while (fast < chars.length && chars[pre] == chars[fast]) {
                fast++;
            }
            chars[slow++] = chars[pre];
            if (fast - pre > 1) {
                String intToString = Integer.toString(fast - pre);
                for (int i = 0; i < intToString.length(); ++i) {
                    chars[slow++] = intToString.charAt(i);
                }
            }
        }
        return slow;
    }
    
    // Time Complexity: O(n)
    // Space Complexity: O(1)
}