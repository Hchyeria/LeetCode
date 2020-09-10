class Solution {
    // BFS
    private static class Node {
        int x;
        int y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private static final int[][] dicrs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        // also can use a hashSet to store i * n + j, convert to 1-D array
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    bfs(grid, visited, m, n, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    
    private void bfs(char[][] grid, boolean[][] visited, int m, int n,
                    int startX, int startY) {
        // use the x*n + y, the space will less
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startX, startY));
        visited[startX][startY] = true;
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int[] ps : dics) {
                int x = node.x + ps[0];
                int y = node.y + ps[1];
                if (isArea(x, y, m, n) && !visited[x][y] && grid[x][y] == '1') {
                    queue.offer(new Node(x, y));
                    visited[x][y] = true;
                }
            }
        }
    }

    private boolean isArea(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >=0 && y < n;
    }

    // Time Complexity: O((m*n) + E(between 1's))
    // Space Complexity: O(1's)

    // DFS
    // trick: use grid itself to record visited
    private int m;
    private int n;
    
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    
    
    private void dfs(char[][] grid,int startX, int startY) {
        grid[startX][startY] = '0';
        for (int[] d : dirs) {
            int x = startX + d[0];
            int y = startY + d[1];
            if (isArea(x, y) && grid[x][y] == '1') {
                dfs(grid, x, y);
            }
        }
    }
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >=0 && y < n;
    }

    // Time Complexity: O((m*n) + E(1's))
    // Space Complexity: O(call-stack)
}