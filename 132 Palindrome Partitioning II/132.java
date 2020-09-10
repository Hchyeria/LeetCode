class Solution {
    public int minCut(String s) {
        // corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        for (int i = 1; i <= len; ++i) {
            int minVal = Integer.MAX_VALUE;
            for (int j = 0; j < i; ++j) {
                if (isPalindrome(s, j, i)) {
                    if (j == 0) {
                        minVal = 0;
                        break;
                    }
                    minVal = Math.min(minVal, dp[j] + 1);
                }
            }
            dp[i] = minVal;

        }
        return dp[len];
    }

    private boolean isPalindrome(String input, int left, int right) {
        right--;
        while (left < right) {
            if (input.charAt(left++) != input.charAt(right--)) {
                return false;
            }
        }
        return true;

    }

    // Time Complexity: O(n ^ 2 * n) -> O(n ^ 3)
    // Time Complexity: O(n)
}