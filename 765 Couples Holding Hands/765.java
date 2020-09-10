class Solution {
    // Solution 1: solution using cyclic swapping (like lc41 and lc268)
    // Time = O(n)
    // Space = O(n)
    public int minSwapsCouples(int[] row) {
        int n = row.length;

        int[] pos = new int[n];
        for (int i = 0; i < n; ++i) {
            pos[row[i]] = i;
        }

        int res = 0;
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while ((j = part(pos[part(row[i])])) != i) {
                swap(row, i, j);
                swap(pos, row[i], row[j]);
                res++;
            }
        }
        return res;
    }
    
    private int part(int i) {
        return i ^ 1; // i % 2 == 0 ? i + 1 : i - 1;
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}

class Solution {
    // Solution 2: use UnionFind to find the number of connected components
    // need to understand cyclic permutation
    // or you can use dfs(O(n)) to find the number of connected components
    // Time = O(log(n))
    // Space = O(n)
    
    private static class UnionFind {
        int[] parent;
        int[] rank;
        int size;
        
        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
            rank = new int[n];
            size = n;
        }
        
        int find(int i) {
            while (parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
        
        boolean union(int i, int j) {
            int p = find(i);
            int q = find(j);
            if (p == q) return false;
            if (rank[p] < rank[q]) {
                parent[p] = q;
            } else if (rank[p] > rank[q]) {
                parent[q] = p;
            } else {
                parent[q] = p;
                rank[p]++;
            }
            size--;
            return true;
        }
        
        boolean isConnect(int i, int j) {
            return find(i) == find(j);
        }
        
    }
    
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        UnionFind uf = new UnionFind(n / 2);
        for (int i = 0; i < n - 1; i += 2) {
            int p = findIndex(row[i]);
            int q = findIndex(row[i + 1]);
            if (p != q) {
                uf.union(p, q);
            }
        }
        
        return n / 2 - uf.size;
    }
    
    private int findIndex(int i) {
        return i / 2;
    }

    

}