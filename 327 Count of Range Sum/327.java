class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // pay attention, must n + 1
        // otherwise, will miss the case , the first i sum
        // the first i sum is preSum[i], not need to -preSum[j], j < i
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = i == 0 ? nums[i] : preSum[i] + nums[i];
        }
        long[] helper = new long[n + 1];
        return mergeSort(preSum, 0, n, lower, upper, helper);
    }

    private int mergeSort(long[] sum, int start, int end, int lower, int upper, long[] helper) {
        if (start >= end) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        int left = mergeSort(sum, start, mid, lower, upper, helper);
        int right = mergeSort(sum, mid + 1, end, lower, upper, helper);
        return merge(sum, start, mid, end, lower, upper, helper) + left + right;
    }

    private int merge(long[] sum, int start, int mid, int end, int lower, int upper, long[] helper) {
        int left = start, right = mid + 1, p = mid + 1, k = mid + 1, t = start;
        System.arraycopy(sum, start, helper, start, end - start + 1);
        int count = 0;
        while (left <= mid) {
            while (k <= end && helper[k] - helper[left] < lower) ++k;
            while (p <= end && helper[p] - helper[left] <= upper) ++p;
            while (right <= end && helper[right] < helper[left]) {
                sum[t++] = helper[right++];
            }
            sum[t++] = helper[left++];
            count += p - k;
        }

        return count;
    }
    // Time = O(n * log(n))
    // Space = O(n helper, preSum + log(n) call-stack)
}

// Solution 2: Segment Tree
class Solution {
    
    private static class SegmentNode {
        long l;
        long r;
        int k;
        long lazy;
        SegmentNode left, right;
        
        long mid() {
            return l + (r - l) / 2;
        }
        
        SegmentNode(long l, long r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
        }
    }
    
    private int query(SegmentNode node, long start, long end) {
        if (start > end || node == null || node.l > end || node.r < start) {
            return 0;
        }
        if (start <= node.l && node.r <= end) {
            return node.k;
        }
        normalize(node);
        return query(node.left, start, end) + query(node.right, start, end);
    } 
    
    private void insert(SegmentNode node, long start, long end, int val) {
        if (start > end || node == null || node.l > end || node.r < start) {
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
            long mid = node.mid();
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
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        long[] preSum = new long[n + 1];
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = i == 0 ? nums[i] : preSum[i] + nums[i];
            min = Math.min(min, preSum[i + 1]);
            max = Math.max(max, preSum[i + 1]);
        }
        
        // careful !!!
        // Math.min(min, 0), Math.max(max, 0)
        SegmentNode root = new SegmentNode(Math.min(min, 0), Math.max(max, 0), 0);
        int res = 0;

        for (long i : preSum) {
            res += query(root, i - upper, i - lower);
            insert(root, i, i, 1);
        }
        
        return res;
    }

    // Time = O(n * log(n))
    // Space = O(Math.max(max, 0) - Math.min(min, 0))
}