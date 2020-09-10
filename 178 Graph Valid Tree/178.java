public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    
    private static class UnionFind {
        int[] parent;
        
        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }
        
        boolean isConnect(int i, int j ) {
            return find(i) == find(j);
        }
        
        int find (int i) {
            while (parent[i] != i) {
                parent[i] =parent[parent[i]];
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
    }
    
    public boolean validTree(int n, int[][] edges) {
        // write your code here
        if (n == 0 || edges == null || (n != edges.length + 1)) {
            return false;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return false;
            }
        }
        return true;
        
    }
    // Time = O(V * log*(N))
    // Space = O(V)
}