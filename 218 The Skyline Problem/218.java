class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return res;
        }
        // can't use Map<> map = new TreeMap.....
        // otherwise you will find the firstKey() doesn't exit
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        
        List<int[]> points = new ArrayList<>();
        for (int[] b : buildings) {
            points.add(new int[] {b[0], -b[2]});
            points.add(new int[] {b[1], b[2]});
        }
        
        Collections.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        map.put(0, 1);
        int preKey = 0;
        for (int[] p : points) {
            if (p[1] < 0) {
                map.put(-p[1], map.getOrDefault(-p[1], 0) + 1);
            } else {
                int count = map.get(p[1]);
                if (count == 1) {
                    map.remove(p[1]);
                } else {
                    map.put(p[1], count - 1);
                }
            }
            int key = map.firstKey();
            if (key != preKey) {
                res.add(Arrays.asList(p[0], key));
                preKey = key;
            }
        }
        return res;
    }
    // Time = O(n * log(n))
    // Space = O(n)
}

// Solution 2: Segment Tree
class Solution {
    private static class SegmentNode {
        int start;
        int end;
        int k;
        int lazy;
        SegmentNode left, right;

        SegmentNode(int s, int e, int k) {
            start = s;
            end = e;
            this.k = k;
        }
    }

    private void insert(SegmentNode node, int start, int end, int val) {
        if (node == null || start > end || start > node.end || end < node.start) {
            return;
        }

        if (start <= node.start && node.end <= end) {
            node.k = Math.max(node.k, val);
            node.lazy = Math.max(node.lazy, val);
            return;
        }
        normalize(node);
        insert(node.left, start, end, val);
        insert(node.right, start, end, val);
        node.k = Math.max(node.left.k, node.right.k);
    }

    private int query(SegmentNode node, int start, int end) {
        if (node == null || start > end || start > node.end || end < node.start) {
            return 0;
        }
        if (start <= node.start && node.end <= end) {
            return node.k;
        }
        normalize(node);
        return Math.max(query(node.left, start, end), query(node.right, start, end));
    }

    private void normalize(SegmentNode node) {
        if (node.start < node.end) {
            if (node.left == null || node.right == null) {
                int mid = node.start + (node.end - node.start) / 2;
                // node should be initialize as node.k, not 0
                node.left = new SegmentNode(node.start, mid, node.k);
                node.right = new SegmentNode(mid + 1, node.end, node.k);
            } else {
                node.left.k = Math.max(node.left.k, node.lazy);
                node.left.lazy = Math.max(node.left.lazy, node.lazy);

                node.right.k = Math.max(node.right.k, node.lazy);
                node.right.lazy = Math.max(node.right.lazy, node.lazy);
            }
        }
        node.lazy = 0;
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return res;
        }
        int n = buildings.length;
        int min = buildings[0][0], max = buildings[n - 1][1];

        List<int[]> points = new ArrayList<>();
        for (int[] b : buildings) {

            points.add(new int[] {b[0], -b[2]});
            points.add(new int[] {b[1], b[2]});
            min = Math.min(min, b[0]);
            max = Math.max(max, b[1]);
        }
        SegmentNode root = new SegmentNode(min, max, 0);
        for (int[] b : buildings) {
            insert(root, b[0], b[1] - 1, b[2]);
        }
        Collections.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        int pre = 0;
        int preStart = min;
        for (int[] p : points) {
            if (p[1] > 0) {
                preStart = p[0];
            }
            int c = query(root, preStart, p[0]);
            if (c != pre) {
                res.add(Arrays.asList(p[0], c));
                pre = c;
            }

        }
        return res;
    }

    // Time = O(n * log(n) sort + n*log(max - min))
    // Space = O(max - min)
}