class Solution {
    // BFS
    private int m;
    private int n;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        m = grid.length;
        n = grid[0].length;
        
        if (grid[r0][c0] == color) {
            return grid;
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(r0 * n + c0);
        visited.add(r0 * n + c0);
        int oldColor = grid[r0][c0];
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int x = node / n;
            int y = node % n;
            if (checkBoundry(visited, grid, x, y, oldColor)) {
                grid[x][y] = color;
            } 
            for (int[] d : dirs) {
                int nextX = x + d[0];
                int nextY = y + d[1];
                if (isArea(nextX, nextY) 
                    && !visited.contains(nextX * n + nextY) 
                    && grid[nextX][nextY] == oldColor) {
                    queue.offer(nextX * n + nextY);
                    visited.add(nextX * n + nextY);
                }
            }
        }
        
        return grid;
        
    }
    
    
    private boolean checkBoundry(Set<Integer> visited, int[][] grid, int x, int y, int color) {
        for (int[] d : dirs) {
            int nextX = x + d[0];
            int nextY = y + d[1]; 
            if (!isArea(nextX, nextY) 
                || ( !visited.contains(nextX * n + nextY) && grid[nextX][nextY] != color)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    // DFS
    public int[][] colorBorder2(int[][] grid, int r0, int c0, int color) {
        m = grid.length;
        n = grid[0].length;
        
        if (grid[r0][c0] == color) {
            return grid;
        }
        
        Set<Integer> visited = new HashSet<>();
        dfs(grid, visited, r0, c0, color, grid[r0][c0]);
        
        return grid;
        
    }
    
    private void dfs(int[][] grid, Set<Integer> visited, int x, int y, int color, int oldColor) {
        visited.add(x * n + y);
        if (checkBoundry(visited, grid, x, y, oldColor)) {
            grid[x][y] = color;
        }
        for (int[] d : dirs) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            if (isArea(nextX, nextY) 
                && !visited.contains(nextX * n + nextY) 
                && grid[nextX][nextY] == oldColor) {
                dfs(grid, visited, nextX, nextY, color, oldColor);
            }
        }
    }
}