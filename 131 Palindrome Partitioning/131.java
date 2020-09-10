class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        char[] input = s.toCharArray();
        int n = input.length;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (i == j 
                    || (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]))) {
                    dp[i][j] = true;        
                }
            }
        }
        dfs(res, new ArrayList<>(), input, dp, 0);
        return res;
    }
    
    private void dfs(List<List<String>> res, List<String> cur, char[] s, boolean[][] dp, int index) {
        if (index == s.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        
        for (int i = index; i < s.length; ++i) {
            if (dp[index][i]) {
                cur.add(new String(s, index, i - index + 1)); // use substring() will cause O(n)
                dfs(res, cur, s, dp, i + 1);
                cur.remove(cur.size() - 1);
            }
        }
        
    }

    // Time Complexity: O(n^2 + 2^(n)), O(n^2) build the dp array, O(2^n) do the all possible partition
    // Space Complexity: O(n^2 + n)

    // Roughly,
    // T(n) = T(n-1)+T(n-2)+..+T(1)
    // T(n+1) = T(n)+T(n-1)+..+T(1)
    // T(n+1) = 2T(n)
    // T(n) = 2^n
}