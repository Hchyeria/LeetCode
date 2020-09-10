class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int pre = prices[0];
        int n = prices.length;
        int sum = 0;
        for (int i = 1; i < n; ++i) {
            if (prices[i] > pre) {
                sum += prices[i] - pre;
            }
            pre = prices[i];
        }
        return sum;
    }
    // Time = O(n)
    // Space = O(1)
}