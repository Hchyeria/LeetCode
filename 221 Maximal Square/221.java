class Solution {
    // DP
    // dp[i][j]: represents the largest length of square, from position (0, 0) to (i, j)
    // dp[i][j] = min(dp[i-1][j-1], dp[i-1][j]. dp[i][j-1]) + 1 if matrix[i][j] == 1
    //              0 , otherwise
    
    public int maximalSquare(char[][] matrix) {
        // need to consider corner case: [], [[]]
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[] cur = new int[n];
        int globalMax = Integer.MIN_VALUE;
        int[] pre = new int[n];
        for (int i = 0; i < n; ++i) {
            pre[i] = matrix[0][i] - '0';
            // need to consider corner case:  [[1]]
            globalMax = Math.max(globalMax, pre[i]);
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j] - '0';
                if (j == 0) {
                    cur[j] = temp;
                } else {
                    if (temp == 0) {
                        cur[j] = 0;
                    } else {
                        cur[j] = min(new int[] {cur[j - 1] + 1, pre[j] + 1, pre[j - 1] + 1});
                    }
                }
                globalMax = Math.max(globalMax, cur[j]);
            }
            pre = Arrays.copyOfRange(cur, 0, n);
        }
        return globalMax * globalMax;
    }
    }

    private int min(int[] array) {
        int res = array[0];
        for (int i : array) {
            res = Math.min(res, i);
        }
        return res;
    }

    // Time complexity is O(m * n)
    // Space complexity is O(n)
}