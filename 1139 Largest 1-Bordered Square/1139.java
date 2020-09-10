class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] left = new int[m][n];
        int[][] up = new int[m][n];
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                left[i][j] = j > 0 && grid[i][j] == 1 ? left[i][j - 1] + 1 : grid[i][j];
                up[i][j] = i > 0 && grid[i][j] == 1 ? up[i - 1][j] + 1 : grid[i][j];
                
                for (int k = Math.min(left[i][j], up[i][j]); k > 0; --k) {
                    if (left[i - k + 1][j] >= k && up[i][j - k + 1] >= k) {
                        res = Math.max(res, k);
                        break;
                    }
                }
            }
        }
        return res * res;
    }
    // Time complexity: O(m * n * min(m,n))
    // Space complexity: O(m*n)
}