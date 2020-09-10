class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        }
        HashSet<Character> distinct = new HashSet<>();
        int fast = 0, slow = 0;
        int result = 0;
        while (fast < s.length()) {
            if (distinct.add(s.charAt(fast))) {
                fast++;
                // result = Math.max(result, distinct.size());
                result = Math.max(result, fast - slow);
            } else {
                distinct.remove(s.charAt(slow));
                slow++;
            }
        }
        return result;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
}