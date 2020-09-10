class Solution {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (!set.add(s.charAt(i))) {
                set.remove(s.charAt(i));
            }
        }
        int odd = set.size();
        return n - (odd == 0 ? 0 : odd - 1);
    }

    // Time = O(n)
    // Space = O(n)
}