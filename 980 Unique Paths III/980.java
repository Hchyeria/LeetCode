class Solution {
    
    private int m, n;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        
        int visited = 0;
        int start = 0, end = 0;
        int left = m * n;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    start = i * n + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == 2) {
                    end = i * n + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == -1) {
                    left--;
                }
            }
        }
        
        return dfs(grid, visited, start, end, left);
    }
    
    private int dfs(int[][] grid, int visited, int start, int end, int left) {
        
        int x = start / n, y = start % n;
        left--;
        visited ^= (1 << start);
        if (start == end && left == 0) {
            visited ^= (1 << start);
            return 1;
        }
        int count = 0;
        for (int[] d : dirs) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            int next = nextX * n + nextY;
            if (isArea(nextX, nextY)
                && ((visited & (1 << next)) == 0)
                && grid[nextX][nextY] == 0) {
                count += dfs(grid, visited, next, end, left);
            }
        }
        
        visited ^= (1 << start);
        return count;
    }
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    // Time Complexity: O(n!)
    // Space Complexity: O(1), visited use bit map
}