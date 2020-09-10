class Solution {
    // Solution 1: divided into three states
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int n = prices.length;

        int sold = 0, hold = Integer.MIN_VALUE, rest = 0;
        for (int i = 0; i < n; ++i) {
            int preSold = sold;
            sold = hold + prices[i];
            hold = Math.max(hold, rest - prices[i]);
            rest = Math.max(rest, preSold);
        }
        return Math.max(sold, rest);
    }

    // Time = O(n)
    // Space = O(1)

    // Solution 2: much better understanding
    // hold[i] = max(hold[i - 1], unhold[i - 2] - A[i]);, can not be unhold[i - 1] - A[i]), because unhold[i - 1] maybe sold just now
    // unhold[i] = max(unhold[i - 1], hold[i - 1] + A[i]);
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int n = prices.length;

        int unhold = 0, hold = -prices[0];
        int unhold2 = 0, preHold2 = 0;
        for (int i = 1; i < n; ++i) {
            preHold2 = unhold2;
            unhold2 = unhold;
            unhold = Math.max(unhold, hold + prices[i]);
            hold = Math.max(hold, preHold2 - prices[i]);
            
        }
        return unhold;
    }
    // Time = O(n)
    // Space = O(1)
}