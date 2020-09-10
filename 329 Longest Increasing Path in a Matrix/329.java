class Solution {
    // Solution 1: DFS + Memoization
    // Time = O(m * n), Space = O(m * n)
    private int m;
    private int n;
    private int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        m = matrix.length;
        n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                max = Math.max(max, dfs(matrix, i, j, cache));
            }
        }
        return max;
    }
    
    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] > 0) {
            return cache[i][j];
        }
        
        int len = 1;
        for (int[] d : DIRS) {
            int x = i + d[0];
            int y = j + d[1];
            if (isArea(x, y) && matrix[x][y] > matrix[i][j]) {
                len = Math.max(len, 1 + dfs(matrix, x, y, cache));
            }
        }
        cache[i][j] = len;
        
        return len;
    }
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}

// Solution 2: Topological Sort
class Solution {
    private int m;
    private int n;
    private int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        m = matrix.length;
        n = matrix[0].length;
        int[][] inDegree = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int[] d : DIRS) {
                    int x = i + d[0];
                    int y = j + d[1];
                    if (isArea(x, y) && matrix[x][y] > matrix[i][j]) {
                        inDegree[i][j]++;
                    }
                }
                
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (inDegree[i][j] == 0) {
                    queue.offer(i * n + j);
                }
            }
        }
        
        int len = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int node = queue.poll();
                int x = node / n;
                int y = node % n;
                for (int[] d : DIRS) {
                    int nextX = x + d[0];
                    int nextY = y + d[1];
                    if (isArea(nextX, nextY) && matrix[nextX][nextY] < matrix[x][y]
                        && --inDegree[nextX][nextY] == 0) {
                        queue.offer(nextX * n + nextY);
                    }
                }
            }
            len++;
        }
        
        return len;
    }
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}