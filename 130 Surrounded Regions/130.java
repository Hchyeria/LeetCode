class Solution {
    private int m;
    private int n;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void solve(char[][] board) {
        if (board == null || board.length <= 2 || board[0].length <= 2) {
            return;
        }
        
        m = board.length;
        n = board[0].length;
        Set<Integer> visited = new HashSet<>();
        visitBoundary(board, visited);
        
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (board[i][j] == 'O' 
                    && !visited.contains(i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    private void visitBoundary(char[][] board, Set<Integer> visited) {
        for (int j = 0; j < n; ++j) {
            if (!visited.contains(j)
                && board[0][j] == 'O') {
                bfs(board, visited, 0, j);
            }
            if (!visited.contains((m - 1) * n + j)
                && board[m - 1][j] == 'O') {
                bfs(board, visited, m - 1, j);
            }
        }
        
        for (int i = 0; i < m; ++i) {
            if (!visited.contains(i * n)
                && board[i][0] == 'O') {
                bfs(board, visited, i, 0);
            }
            if (!visited.contains(i * n + (n - 1))
                && board[i][n - 1] == 'O') {
                bfs(board, visited, i, n - 1);
            }
        }
    }       
              
    // BFS
    private void bfs(char[][] board, Set<Integer> visited, int x, int y) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x * n + y);
        visited.add(x * n + y);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int nodeX = node / n;
            int nodeY = node % n;
            for (int[] d : dirs) {
                int nextX = nodeX + d[0];
                int nextY = nodeY + d[1];
                if (isArea(nextX, nextY) 
                    && board[nextX][nextY] == 'O'
                    && !visited.contains(nextX * n + nextY)) {
                    queue.offer(nextX * n + nextY);
                    visited.add(nextX * n + nextY);
                }
            }
        }
    }
    

    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    // DFS
    private void dfs(char[][] board, Set<Integer> visited, int x, int y) {
        visited.add(x * n + y);
        for (int[] d : dirs) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            if (isArea(nextX, nextY) 
                && board[nextX][nextY] == 'O'
                && !visited.contains(nextX * n + nextY)) {
                dfs(board, visited, nextX, nextY);
            }
        }
    }
}