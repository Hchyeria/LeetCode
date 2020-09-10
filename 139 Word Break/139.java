class Solution {
    // DP
    // dp[i]: represents whether first i size chars of string can be segmented
    // dp[i]: whether no cut in the dictionary || (dp[j] && substring(j, i) whether in the dictionary) for j in range [1, i - 1]
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i - 1; ++j) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    // n: length of s
    // m: size of dict
    // Time Complexity: O(m + n^2*n), because of substring API
    // Space Complexity: O(m + n)

    // Follow up: return all possible compositions?
    // LeetCode #140 (Word Break II)
}