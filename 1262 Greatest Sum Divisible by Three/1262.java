class Solution {
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[2][3];
        int old = 1, now = 1;
        for (int num : nums) {
            old = now;
            now = 1 - now;
            System.arraycopy(dp[old], 0, dp[now], 0, 3);
            for (int pre : dp[old]) {
                int sum = pre + num;
                int index = sum % 3;
                dp[now][index] = Math.max(dp[now][index], sum);
            }
        }
        return dp[now][0];
    }
    // Time = O(n)
    // Space = O(1)
}