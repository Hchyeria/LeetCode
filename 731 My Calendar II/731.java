// Solution 1: TreeMap
class MyCalendarTwo {
    private TreeMap<Integer, Integer> map;
    public MyCalendarTwo() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int capacity = 2;

        for (int v : map.values()) {
            capacity -= v;
            if (capacity < 0) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }
        return true;
    }

    // Time Complexity: O(n*log(n))
    // Space Complexity: O(1)
    
    // if we write like this will throw ConcurrentModificationException
    // The difference is that, the correct answer simply returns after removal
    // Basically, ConcurrentModificationException is thrown when the iterator checks the size of the map in method "iterator.hasNext()"
    public boolean bookWillThrowException(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int capacity = 2;
        boolean flag = true;
        for (int v : map.values()) {
            capacity -= v;
            if (capacity < 0) {
                map.put(start, map.get(start) - 1);
                if(map.get(start) == 0) map.remove(start);
                map.put(end, map.get(end) + 1);
                if(map.get(end) == 0) map.remove(end);
                flag = false;
            }
        }
        return flag;
    }

   
}
// Time Complexity: O(n * n*log(n)), book() cause O(n*log(n))
// Space Complexity: O(n)


/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */


// Solution 2: Segment tree with lazy propagation
class MyCalendarTwo2 {
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
    public MyCalendarTwo() {
        // Math.pow() return type is double
        root = new SegmentTree(0, MAX_NODE, 0);
    }
    
    public boolean book(int start, int end) {
        int bookSize = query(root, start, end - 1);
        if (bookSize >= 2) {
            return false;
        }
        update(root, start, end - 1, 1);
        return true;
    }
    // Time Complexity: O(log(d))
    // Space Complexity: O(log(d))

}

// Time Complexity: O(n*log(d)), book() cause O(log(d)) where d is the maximum range allowed for all events(in this case d = 10^9)
// Space Complexity: O(n*log(d) + node) in the worst case, the segment tree can be a full binary tree with 2d nodes.
// However, this is very unlikely as it would require a total of d calls of the book function each with an event of range 1
// For n calls of the book function, the average space cost is roughly O(n*log(d))