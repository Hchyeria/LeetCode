/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    /**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
     
    private static class UnionFind {
        int[] parent;
        int count;
        
        UnionFind(int n) {
            parent = new int[n];
        }
        
        void addLand(int i) {
            if (parent[i] > 0) {
                return;
            }
            parent[i] = i;
            count++;
        }
        
        int find(int i) {
            while (parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
        
        void union(int i, int j) {
            int q = find(i);
            int p = find(j);
            if (q != p) {
                parent[q] = p;
                count--;
            }
        } 
        
        int numberOfLand() {
            return count;
        }
        
        boolean isLand(int i) {
            return parent[i] > 0;
        }
    }
    
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int row, col;
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // write your code here
        if ((n == 0 && m == 0) || operators == null || operators.length == 0) {
            return Collections.emptyList();
        }
        row = n;
        col = m;
        UnionFind uf = new UnionFind(row * col + 1);
        List<Integer> res = new ArrayList<>();
        for (Point p : operators) {
            int position = p.x * col + p.y + 1;
            uf.addLand(position);
            
            for (int[] d : DIRS) {
                int x = p.x + d[0];
                int y = p.y + d[1];
                if (isArea(x, y) && uf.isLand(x * col + y + 1)) {
                    uf.union(position, x * col + y + 1);
                } 
            } 
            res.add(uf.numberOfLand());
        }
        
        return res;
    }
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    // Time = O(n * 4 * log*(n))
    // Space = O(n)
}