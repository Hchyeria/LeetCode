class Solution {
    public int mergeStones(int[] stones, int K) {
       int n = stones.length;
       if (!isValid(n, K)) {
           return -1;
       }
       int[] preSum = buildPreSum(stones, n);
       int[][] dp = new int[n][n];
       int step = K - 1;
       for (int i = n - 1; i >= 0; --i) {
           for (int j = i + step; j < n; ++j) {
               dp[i][j] = Integer.MAX_VALUE;
               for (int k = i; k < j; k += step) {
                   dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
               }
               if (isValid(j - i + 1, K)) {
                   int sum = i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
                   dp[i][j] += sum;
               }
           }
       }
       return dp[0][n - 1];

   }

   private int[] buildPreSum(int[] array, int n) {
       int[] preSum = new int[n];
       int sum = 0;
       for (int i = 0; i < n; ++i) {
           sum += array[i];
           preSum[i] = sum;
       }
       return preSum;

   }

   private boolean isValid(int length, int k) {
       return (length - 1) % (k - 1) == 0;
   }

   // Time Complexity: O(n ^ 3)
   // Space Complexity: O(n ^ 2)
}