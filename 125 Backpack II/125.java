public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        // write your code here
        
        if (m == 0 || A == null || A.length == 0
            || V == null || V.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] dp = new int[m + 1];
        
        for (int i = 1; i <= n; ++i) {
            for (int j = m; j >= 0; --j) {
                if (j - A[i - 1] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[m];
    }

    // Time = O(m * n)
    // Space = O(m)
}