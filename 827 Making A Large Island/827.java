class Solution {
    // I just want practice union find set
    // actually, this method isn't good
    // we can change grid node value to their index (index id partition identify id)
    // and use Hash map to store the size
    // both can be done in DFS
    // then loop every 0
    private static class UnionFind {
        int[] parent;
        int[] rank;
        
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }
        
        int find(int index) {
            while (parent[index] != index) {
                parent[index] = parent[parent[index]];
                index = parent[index];
            }

            return index;
        }
        
        boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
        
        void union(int x, int y) {
            int p = find(x);
            int q = find(y);
            if (rank[p] < rank[q]) {
                parent[p] = q;
            } else if (rank[p] > rank[q]) {
                parent[q] = parent[p];
            } else {
                parent[p] = q;
                rank[q] += 1;
            }
        }
        
    }
    
    private int m;
    private int n;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int largestIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        UnionFind unionFind = new UnionFind(m * n);
        int globalMax = 0;
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1
                    && !visited.contains(i * n + j)) {
                    int size = dfs(grid, visited, unionFind, i, j);
                    map.put(unionFind.find(i * n + j), size);
                    globalMax = Math.max(globalMax, size);
                }
            }
        }
        
        
       for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int curSize = 0;
                    // need set to deduplicate
                    Set<Integer> seen = new HashSet<>();
                    for (int[] d : dirs) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (isArea(x ,y) 
                            && grid[x][y] == 1) {
                            int root = unionFind.find(x * n + y);
                            if (!seen.contains(root)) {
                                curSize += map.get(root);
                                seen.add(root);
                            }
                        }
                    }
                    curSize++;
                    globalMax = Math.max(globalMax, curSize);
                }
            }
        }
        return globalMax;
    }
    
    private int dfs(int[][] grid, Set<Integer> visited, 
                    UnionFind unionFind, int x, int y) {
        int count = 1;
        visited.add(x * n + y);
        for (int[] d : dirs) {
            int i = x + d[0];
            int j = y + d[1];
            if (isArea(i ,j)
                && grid[i][j] == 1
                && !visited.contains(i * n + j)) {
                count += dfs(grid, visited, unionFind, i, j);
                unionFind.union(x * n + y, i * n + j);
            } 
        }
        
        return count;
    }
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
    
}
