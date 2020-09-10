// 516. Paint House II
// LintCode

public class Solution {
    /**
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        // write your code here
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int min1 = 0, min2 = 0;
        int id = -1;
        int n = costs.length;
        int k = costs[0].length;
        
        for (int i = 0; i < n; ++i) {
            int curMin1 = Integer.MAX_VALUE, curMin2 = Integer.MAX_VALUE;
            int curId = -1;
            for (int j = 0; j < k; ++j) {
                int cur = 0;
                if (j != id) {
                    cur = min1 + costs[i][j];
                } else {
                    cur = min2 + costs[i][j];
                }

                if (cur < curMin1) {
                    // remember before update the min
                    // let the value pass to min2
                    curMin2 = curMin1;
                    curMin1 = cur;
                    curId = j;
                } else if (cur < curMin2) {
                    curMin2 = cur;
                }
            }
            min1 = curMin1;
            min2 = curMin2;
            id = curId;
        }
        
        return min1;
    }

    // Time Complexity: O(m * k)
    // Space Complexity: O(1)
}