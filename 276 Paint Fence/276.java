// LintCode 514

public class Solution {
    /**
     * @param n: non-negative integer, n posts
     * @param k: non-negative integer, k colors
     * @return: an integer, the total number of ways
     */
     
    
    // dp
    // same: when last two posts are same
    // diff: when last two posts are diff
    // n == 1, dp[1] = k
    // n == 2
    // same[2] = k, diff[2] = k*(k-1), dp[1] = same[2] + diff[2] = k + k*(k-1)
    // n == 3
    // same[3] = k*(k-1), diff[3] = (k + k*(k-1))*(k-1), dp[3] = k*(k-1) + (k + k*(k-1))*(k-1)
    // same[i] = diff[i - 1]
    // diff[i] = dp[i-1] * (k - 1)
    public int numWays(int n, int k) {
        // write your code here
        if (n < 2) return k;
        int[] dp = new int[n];
        dp[0] = k;
        int same = 0, diff = k;
        for (int i = 1; i < n; ++i) {
            same = diff;
            diff = dp[i - 1] * (k - 1);
            dp[i] = same + diff;
        }
        return dp[n - 1];
        
    }
    // Time = O(n)
    // Space = O(n)

    // optimise space
    public int numWays2(int n, int k) {
        // write your code here
        if (n < 2) return k;

        int pre = k;
        int same = 0, diff = k;
        for (int i = 1; i < n; ++i) {
            same = diff;
            diff = pre * (k - 1);
            pre = same + diff;
        }
        return pre;
        
    }
    // Time = O(n)
    // Space = O(1)
}