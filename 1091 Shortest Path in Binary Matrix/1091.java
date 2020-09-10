class Solution {
    private static class Node {
        int x;
        int y;
        int path;

        Node(int x, int y, int path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }
    }
    
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, 
                                         {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    private int m;
    private int n;
    public int shortestPathBinaryMatrix(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }
        if (m == 1 && n == 1) {
            return 1;
        }
        
        
        // BFS
        // it's not a good way to use array to record visited
        // recommend Hash Table
        boolean[][] visited = new boolean[m][n];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 1));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int count =node.path;
            if (node.x == m - 1 && node.y == n - 1) {
                return node.path;
            }
            for (int[] ps : dirs) {
                int x = node.x + ps[0];
                int y = node.y + ps[1];
                if (isArea(x, y) && !visited[x][y] && grid[x][y] == 0) {
                    queue.offer(new Node(x, y, count + 1));
                    visited[x][y] = true;
                }
            }
        }
        return -1;
    }
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >=0 && y < n;
    }

    // Time Complexity: O(1's + E(1's))
    // Space Complexity: O(m*n(visited) + 1's)
    
}