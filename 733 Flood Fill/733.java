class Solution {
    private int m;
    private int n;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // DFS
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        m = image.length;
        n = image[0].length;
        
        if (image[sr][sc] == newColor) {
            return image;
        }
        Set<Integer> visited = new HashSet<>();
        
        dfs(image, visited, sr, sc, newColor, image[sr][sc]);
        return image;
    }
    
    private void dfs(int[][] image, Set<Integer> visited, 
                     int x, int y, int newColor, int oldColor) {
        visited.add(x * n + y);
        image[x][y] = newColor;
        for (int[] d : dirs) {
            int nextX = x + d[0];
            int nextY = y + d[1];
            if (isArea(nextX, nextY) 
                && !visited.contains(nextX * n + nextY)
                && image[nextX][nextY] == oldColor) {
                dfs(image, visited, nextX, nextY, newColor, oldColor);
            }
        }
    } 
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }


    // BFS
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        m = image.length;
        n = image[0].length;
        int oldColor = image[sr][sc];
        if (oldColor == newColor) {
            return image;
        }
        
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(sr * n + sc);
        queue.offer(sr * n + sc);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int x = node / n;
            int y = node % n;
            image[x][y] = newColor;
            for (int[] d : dirs) {
                int nextX = x + d[0];
                int nextY = y + d[1];
                if (isArea(nextX, nextY) 
                    && !visited.contains(nextX * n + nextY)
                    && image[nextX][nextY] == oldColor) {
                    queue.offer(nextX * n + nextY);
                    visited.add(nextX * n + nextY);
                }
            }
        } 

        return image;
    }
}