class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        int[] helper = new int[n];
        int[] indexArray = new int[n];
        
        for (int i = 0; i < n; ++i) {
            indexArray[i] = i;
            res.add(0);
        }
        mergeSort(nums, 0, n - 1, res, indexArray, helper);
        
        return res;
    }
    
    private void mergeSort(int[] nums, int start, int end, List<Integer> res, 
                           int[] indexArray, int[] helper) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid, res, indexArray, helper);
        mergeSort(nums, mid + 1, end, res, indexArray, helper);
        merge(nums, start, mid, end, res, indexArray, helper);
    }
    
    private void merge(int[] nums, int start, int mid, 
                       int end, List<Integer> res, int[] indexArray, int[] helper) {
        System.arraycopy(indexArray, start, helper, start, end - start + 1);
        int left = start;
        int right = mid + 1;
        int p = start;
        
        while (left <= mid) {
            if (right > end || nums[helper[left]] <= nums[helper[right]]) {
                indexArray[p++] = helper[left];
                res.set(helper[left], res.get(helper[left]) + right - (mid + 1));
                left++;
            } else {
                indexArray[p++] = helper[right++];
            }
        }
    }
    // Time Complexity: O(n * log(n))
    // Space Complexity: O(n)
}

// Solution 2: Segment Tree
class Solution {
    private static class SegmentNode {
        int l;
        int r;
        int k;
        int lazy;

        SegmentNode left, right;

        int mid() {
            return l + (r - l) / 2;
        }

        SegmentNode(int l, int r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
        }

    }

    private int query(SegmentNode node, int start, int end) {
        if (start > end || node == null || start > node.r || end < node.l) {
            return 0;
        }

        if (start <= node.l && node.r <= end) {
            return node.k;
        }
        normalize(node);

        return query(node.left, start, end) + query(node.right, start, end);
    }

    private void insert(SegmentNode node, int start, int end, int val) {
        if (start > end || node == null || start > node.r || end < node.l) {
            return;
        }
        if (start <= node.l && node.r <= end) {
            node.k += val;
            node.lazy += val;
            return;
        }
        normalize(node);

        insert(node.left, start, end, val);
        insert(node.right, start, end, val);

        node.k = node.left.k + node.right.k;
    }

    private void normalize(SegmentNode node) {
        if (node.left == null || node.right == null) {
            int mid = node.mid();
            node.left = new SegmentNode(node.l, mid, 0);
            node.right = new SegmentNode(mid + 1, node.r, 0);
        } else {
            node.left.k += node.lazy;
            node.left.lazy += node.lazy;

            node.right.k += node.lazy;
            node.right.lazy += node.lazy;
        }
        node.lazy = 0;
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i : nums) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        SegmentNode root = new SegmentNode(min, max, 0);
        for (int i = n - 1; i >= 0; --i) {
            res.add(0, query(root, min, nums[i] - 1));
            insert(root, nums[i], nums[i], 1);
        }

        return res;
    }
}
// Time = O(n * log(N))
// Space = O(max - min)

// Solution 3: BinaryIndex Tree
class Solution {
    private int query(int[] tree, int i) {
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= (i & (-i));
        }
        return sum;
    }
    
    private void insert(int[] tree, int i, int val) {
        while (i < tree.length) {
            tree[i] += val;
            i += (i & (-i));
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    
        for (int i : nums) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        // why plus 2??
        // assume that if all elements in the array is the same
        // then max == min, max - min == 0
        // we must guarantee the tree has at least one element, which index == 1
        // so the length of tree at least equal 2
        // the index of min element should equal 1
        // so the nums[i] map to index of nums[i] - min + 1
        // nums[i] - 1 map to nums[i] - min
        int[] binaryIndexTree = new int[max - min + 2];
        
        for (int i = n - 1; i >= 0; --i) {
            res.add(0, query(binaryIndexTree, nums[i] - min));
            insert(binaryIndexTree, nums[i] - min + 1, 1);
        }
    
        return res;
    }
}

// Time = O(n * log(max - min + 2))
// Space = O(max - min + 2)
