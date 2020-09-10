class Solution {
    private int m;
    private int n;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                 if (!visited.contains(i * n + j)
                     && grid[i][j] == 1) {
                     count = Math.max(count, bfs(grid, visited, i, j));
                 }
            }
        }
        return count;
        
    }

    private int bfs(int[][] A, Set<Integer> visited, int i, int j) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * n + j);
        boolean flag = true;
        int count = 0;
        visited.add(i * n + j);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int x = node / n;
            int y = node % n;
            count++;
            for (int[] d : dirs) {
                int nextX = x + d[0];
                int nextY = y + d[1];
                if (isArea(nextX, nextY) 
                    && !visited.contains(nextX * n + nextY)
                    && A[nextX][nextY] == 1) {
                    queue.offer(nextX * n + nextY);
                    visited.add(nextX * n + nextY);
                }
            }
        }
        return count;
    }
    
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    // DFS
    private int dfs(int[][] A, Set<Integer> visited, int i, int j) {
        visited.add(i * n + j);
        int count = 1;
        for (int[] d : dirs) {
            int nextX = i + d[0];
            int nextY = j + d[1];
            if (isArea(nextX, nextY) 
                && !visited.contains(nextX * n + nextY)
                && A[nextX][nextY] == 1) {
                count += dfs(A, visited, nextX, nextY);
            }
        }
        return count;
    }
}