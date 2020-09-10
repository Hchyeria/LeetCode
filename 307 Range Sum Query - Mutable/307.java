// Solution 1： Binary Indexed Tree
class NumArray {
    private long[] tree;
    private int[] A;
    private void add(int i, int val) {
        int n = tree.length;
        while (i < n) {
            tree[i] += val;
            i += (i & (-i));
        }
    }
    // Time = O(log(n))

    private long query(int i) {
        long sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= (i & -i);
        }
        return sum;
    }
    // Time = O(log(n))

    public NumArray(int[] nums) {
        int n = nums.length;
        // Binary Indexed Tree start at index 1
        // so length == n + 1
        tree = new long[n + 1];
        A = new int[n];
        for (int i = 0; i < n; ++i) {
            add(i + 1, nums[i]);
            A[i] = nums[i];
        }
    }
    // Time = O(n * log(n))

    public void update(int i, int val) {
        add(i + 1, val - A[i]);
        A[i] = val;
    }
    // Time = O(log(n))

    public int sumRange(int i, int j) {
        return (int) (i == 0 ? query(j + 1) : query(j + 1) - query(i));
    }
    // Time = O(log(n))
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */


// Solution 2: Segment Tree
class NumArray2 {

    private static class SegmentTree {
        int l;
        int r;
        int v;
        int lazy = 0;
        
        SegmentTree left;
        SegmentTree right;
        
        SegmentTree(int l, int r, int v) {
            this.l = l;
            this.r = r;
            this.v = v;
        }
    }
    
    private void update(SegmentTree root, int start, int end, int val) {
        if (start > end || root == null || start > root.r || end < root.l) {
            return;
        }
        if (start <= root.l && root.r <= end) {
            root.v += val;
            root.lazy += val;
            return;
        }
        
        normalize(root);
        
        update(root.left, start, end, val);
        update(root.right, start, end, val);
        
        root.v = root.left.v + root.right.v;
    }
    
    private int query(SegmentTree root, int start, int end) {
        if (start > end || root == null || start > root.r || end < root.l) {
            return 0;
        }

        if (start <= root.l && root.r <= end) {
            return root.v;
        }
        
        normalize(root);
                
        return query(root.left, start, end) + query(root.right, start, end);
    }
    
    private void normalize(SegmentTree root) {
        if (root.l < root.r) {
            if (root.left == null || root.right == null) {
                int mid = root.l + (root.r - root.l) / 2;
                root.left = new SegmentTree(root.l, mid, 0);
                root.right = new SegmentTree(mid + 1, root.r, 0);
            } else if (root.lazy != 0) {
                root.left.v += root.lazy;
                root.left.lazy += root.lazy;
                
                root.right.v += root.lazy;
                root.right.lazy += root.lazy;
            }
        }
        root.lazy = 0;
    }
    
    private SegmentTree root;
    private int[] A;
    public NumArray(int[] nums) {
        int n = nums.length;
        A = new int[n];
        root = new SegmentTree(0, n - 1, 0);
        for (int i = 0; i < n; ++i) {
            update(root, i, i, nums[i]);
            A[i] = nums[i];
        }
    }
    
    public void update(int i, int val) {
        update(root, i, i, val - A[i]);
        A[i] = val;
    }
    
    public int sumRange(int i, int j) {
        return query(root, i, j);
    }
}
