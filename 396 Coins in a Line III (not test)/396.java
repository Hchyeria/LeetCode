/* 
    There are n coins in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left. 
    The player with the larger amount of money wins.
    Could you please decide the first player will win or lose?

    Example
    Given array A = [3,2,2], return true.
    Given array A = [1,2,4], return true.
    Given array A = [1,20,4], return false.

    Challenge
    Follow Up Question:
    If n is even. Is there any hacky algorithm that can decide whether first player will win or lose in O(1) memory and O(n) time?
    
*/
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length <= 1) {
            return true;
        }
        int n = values.length;
        int[][] dp = new int[n][n];
        // dp[i][j] = max(values[i] - dp[i+1][j], values[j] - dp[i][j-1])

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (i == j) {
                    dp[i][j] = values[i];
                    continue;
                }
                dp[i][j] = values[j] - dp[i][j - 1];
                if (i < n - 1) {
                    dp[i][j] = Math.max(dp[i][j], values[i] - dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1] > 0;
    }
    // Time = O(n * n)
    // Space = O(n*n) -> optimise tp O(n)
}