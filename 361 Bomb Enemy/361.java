// 553. Bomb Enemy
// LintCode

public class Solution {
    /**
     * @param grid: Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return: an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies(char[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] up = new int[m][n];
        int[][] bottom = new int[m][n];
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];
        
        // bomb up and left
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 'W') {
                    up[i][j] = 0;
                    left[i][j] = 0;
                    continue;
                }
                
                int enemy = grid[i][j] == 'E' ? 1 : 0;
                if (i > 0) {
                    up[i][j] += up[i - 1][j];
                }
                if (j > 0) {
                    left[i][j] += left[i][j - 1];
                }
                up[i][j] += enemy;
                left[i][j] += enemy;
            }
        }
        
        // bomb bottom and right
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (grid[i][j] == 'W') {
                    bottom[i][j] = 0;
                    right[i][j] = 0;
                    continue;
                }
                
                int enemy = grid[i][j] == 'E' ? 1 : 0;
                if (i < m - 1) {
                    bottom[i][j] += bottom[i + 1][j];
                }
                if (j < n - 1) {
                    right[i][j] += right[i][j + 1];
                }
                bottom[i][j] += enemy;
                right[i][j] += enemy;
            }
        }
   
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '0') {
                    res = Math.max(res, up[i][j] + bottom[i][j] + left[i][j] + right[i][j]); 
                }
            }
        }
        return res;
    }
}