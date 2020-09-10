class MyCalendarThree {

    private TreeMap<Integer, Integer> map;
    public MyCalendarThree() {
        map = new TreeMap<>();
    }
    
    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int capacity = 0;
        int res = 0;
        for (int v : map.values()) {
            capacity += v;
            res = Math.max(res, capacity);
        }
        return res;
    }
    // Time Complexity: O(n*log(n))
    // Space Complexity: O(1)
}

// Time Complexity: O(n * n*log(n)), book() cause O(n*log(n))
// Space Complexity: O(n)


// Solution 2: Segment tree with lazy propagation
class MyCalendarThree2 {

    private static class SegmentTree {
        int k;
        int l;
        int r;
        int lazy = 0;
        SegmentTree left, right;

        SegmentTree(int l, int r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
            this.left = this.right = null;
        }
    }

    private int query(SegmentTree node, int start, int end) {
        if (start > end || node == null || start > node.r || end < node.l) {
            return 0;
        }
        if (start <= node.l && node.r <= end) {
            return node.k;
        }
        normalize(node);

        return Math.max(query(node.left, start, end), query(node.right, start, end));
    }

    private void update(SegmentTree node, int start, int end, int val) {
        if (start > end || node == null || start > node.r || end < node.l) {
            return;
        }
        if (start <= node.l && node.r <= end) {
            node.k += val;
            node.lazy += val;
            return;
        }
        normalize(node);

        update(node.left, start, end, val);
        update(node.right, start, end, val);

        node.k = Math.max(node.left.k, node.right.k);
    }

    private void normalize(SegmentTree node) {
        if (node.l < node.r) {
            if (node.left == null || node.right == null) {
                int mid = node.l + (node.r - node.l) / 2;
                node.left = new SegmentTree(node.l, mid, node.k);
                node.right = new SegmentTree(mid + 1, node.r, node.k);
            } else if (node.lazy > 0) {
                node.left.k += node.lazy;
                node.left.lazy += node.lazy;

                node.right.k += node.lazy;
                node.right.lazy += node.lazy;
            }
        }
        node.lazy = 0;
    }


    SegmentTree root;
    private final static int MAX_NODE = 1_000_000_000;
    public MyCalendarThree() {
        root = new SegmentTree(0, MAX_NODE, 0);
    }
    
    public int book(int start, int end) {
        update(root, start, end - 1, 1);
        return root.k;
    }
    // Time Complexity: O(log(d))
    // Space Complexity: O(log(d))
}

// Time Complexity: O(n*log(d)), book() cause O(log(d)) where d is the maximum range allowed for all events(in this case d = 10^9)
// Space Complexity: O(n*log(d) + node) in the worst case, the segment tree can be a full binary tree with 2d nodes.
// However, this is very unlikely as it would require a total of d calls of the book function each with an event of range 1
// For n calls of the book function, the average space cost is roughly O(n*log(d))