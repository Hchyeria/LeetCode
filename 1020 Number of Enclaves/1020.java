class Solution {
    
    private int m;
    private int n;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int numEnclaves(int[][] A) {
        m = A.length;
        n = A[0].length;
        int count = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                 if (!visited.contains(i * n + j)
                     && A[i][j] == 1) {
                     count += bfs(A, visited, i, j);
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
            // can't add visited add this time
            // visited.add(nextX * n + nextY);
            if (isBoundary(x, y)) {
                flag = false;
            }
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
        return flag ? count : 0;
    }
    
    private boolean isBoundary(int x, int y) {
        return x == 0 || x == m - 1 || y == 0 || y == n - 1;
    }
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
    
    
}
