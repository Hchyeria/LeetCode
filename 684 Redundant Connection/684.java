class Solution {
    
    private int[] parent;
    
    private int find(int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
    
    boolean union(int i, int j) {
        int q = find(i);
        int p = find(j);
        if (q == p) {
            return false;
        }
        parent[q] = p;
        
        return true;
    }
    
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return new int[2];
        }
        int m = edges.length;
        int n = edges[0].length;
        parent = new int[m * n + 1];
        for (int i = 1; i <= m * n; ++i) {
            parent[i] = i;
        }
        
        for (int[] edge : edges) {
            if (!union(edge[0], edge[1])) {
                return edge;
            }
        }
        
        return new int[2];
    }
}