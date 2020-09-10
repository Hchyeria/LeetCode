class Solution {
    
    private int[] parent;
    
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int m = edges.length;
        int n = edges[0].length;
        
        parent = new int[m * n + 1];
        
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        
        for (int[] edge : edges) {
            if (parent[edge[1]] == 0) {
                parent[edge[1]] = edge[0];
            } else {
                can2 = new int[] {edge[0], edge[1]};
                can1 = new int[] {parent[edge[1]], edge[1]};
                edge[1] = 0;
            }
        }
        
        for (int i = 1; i <= m * n; ++i) {
            parent[i] = i;
        }
        
        for (int[] edge : edges) {
            if (find(edge[0]) == edge[1]) {
                if (can1[0] == -1) {
                    return edge;
                }
                return can1;
            }
            parent[edge[1]] = edge[0];
        }
        return can2;
    }
    
    private int find(int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
}