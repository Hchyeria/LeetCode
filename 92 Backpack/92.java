public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    // scroll array
    public int backPack(int m, int[] A) {
        // write your code here
        
        if (m <= 0) {
            return m;
        }
        
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        boolean[][] dp = new boolean[2][m + 1];
        dp[0][0] = true;
        
        int res = 0;
        int old = 0, now = 0;
        for (int i = 1; i <= n; ++i) {
            old = now;
            now = 1 - now;
            for (int j = 0; j <= m; ++j) {
                dp[now][j] = dp[old][j];
                if (j - A[i - 1] >= 0) {
                    dp[now][j] = dp[now][j] || dp[old][j - A[i - 1]];
                }
                if (dp[now][j]) {
                    res = Math.max(res, j);
                }
            }
        }
        return res;
    }
    // Time = O(m * n)
    // Space = O(2 * m)

    // only single array
    public int backPack2(int m, int[] A) {
        if (m <= 0) {
            return m;
        }
        
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;
        
        int res = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = m; j >= 0; --j) {
                if (j - A[i - 1] >= 0) {
                    dp[j] = dp[j] || dp[j - A[i - 1]];
                }
                if (dp[j]) {
                    res = Math.max(res, j);
                }
            }
        }
        return res;
    }
    // Time = O(m * n)
    // Space = O(m)
}