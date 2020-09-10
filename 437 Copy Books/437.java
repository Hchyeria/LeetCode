// 437 Copy Books
// LintCode

public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
     
    // dp
    // dp[i][k] = min( max(dp[j][k-1], pages[j] + ... + pages[i-1]) for j in [0, i] )
    public int copyBooks(int[] pages, int k) {
        // write your code here
        if (pages == null || pages.length == 0) {
            return 0;
        }
        int n = pages.length;
        int[][] dp = new int[n + 1][k + 1];
        
        for (int i = 1; i <= n; ++i) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        
        for (int i = 1; i <= n; ++i) {
            for (int m = 1; m <= k; ++m) {
                int minVal = Integer.MAX_VALUE;
                int sum = 0;
                for (int j = i; j >= 0; --j) {
                    if (j != i){
                        sum += pages[j];
                    }
                    
                    minVal = Math.min(minVal, Math.max(dp[j][m - 1], sum));
                }
                dp[i][m] = minVal; 
            }
        }
        return dp[n][k];
        
   }

   // Time Complexity: O(n^2 * k)
   // Space Complexity: O(n * k)

    // Binary Search 
    public int copyBooks2(int[] pages, int k) {
        // write your code here
        if (pages == null || pages.length == 0) {
            return 0;
        }
        int n = pages.length;
        
        int left = 0, right = 0;
        for (int page : pages) {
            left = Math.max(left, page);
            right += page;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canCopyByK(pages, mid, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }

    private boolean canCopyByK(int[] pages, int sum, int k) {
        int count = 1;
        int cur = 0;
        for (int page : pages) {
            cur += page;
            if (cur > sum) {
                count++;
                cur = page;
            }
        }
        return count <= k;
    } 
    // Time Complexity: O(log(sum) * n)
    // Space Complexity: O(1)
}


