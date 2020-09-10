class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int hold = -prices[0], unHold = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            int preHold = hold;
            hold = Math.max(hold, unHold - prices[i]);
            unHold = Math.max(unHold, preHold + prices[i] - fee);
        }
        return unHold;
    }

    // Time = O(n)
    // Space = O(1)
}