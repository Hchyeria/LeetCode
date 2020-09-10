class Solution {
    public int removePalindromeSub(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return 2;
            }
        }
        return 1;
        
    }
    // Time = O(n)
    // Space = O(1)
}