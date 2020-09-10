class Solution {
    // Solution 1: use TreeSet to do Binary Search
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length, col = matrix[0].length;
        int m = Math.min(row, col);
        int n = Math.max(row, col);
        boolean isColBig = n == col;
        long[] sum = new long[n + 1];
        int res = Integer.MIN_VALUE;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < m; ++i) {
            long[] preSum = new long[n];
            for (int j = i; j < m ; ++j) {
                set.clear();
                set.add(0L);
                for (int p = 0; p < n; ++p) {
                    preSum[p] += isColBig ? matrix[j][p] : matrix[p][j];
                    sum[p + 1] = sum[p] + preSum[p];
                    Long cur = set.ceiling(sum[p + 1] - k);
                    if (cur != null) {
                        res = Math.max(res, (int) (sum[p + 1] - cur));
                        if (res == k) {
                            return k;
                        }
                    }
                    set.add(sum[p + 1]);
                }
            }
        }
        return res;
    }
    // min = min(m, n), max = max(m, n)
    // Time = O(min ^ 2 * max*loh(max))
    // Space = O(max)
    
    // Solution 2: use mergeSort
    public int maxSumSubmatrix2(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int row = matrix.length, col = matrix[0].length;
        int m = Math.min(row, col);
        int n = Math.max(row, col);
        boolean isColBig = n == col;
        long[] sum = new long[n + 1];
        int res = Integer.MIN_VALUE;
        long[] helper = new long[n + 1];
        for (int i = 0; i < m; ++i) {
            long[] preSum = new long[n];
            for (int j = i; j < m ; ++j) {
                long sub = Integer.MIN_VALUE;
                long curMax = Integer.MIN_VALUE;
                for (int p = 0; p < n; ++p) {
                    preSum[p] += isColBig ? matrix[j][p] : matrix[p][j];
                    sum[p + 1] = sum[p] + preSum[p];

                    sub = Math.max(sub + preSum[p], preSum[p]);
                    curMax = Math.max(curMax, sub);
                }
                if (curMax <= k) {
                    res = Math.max(res, (int) curMax);
                } else {
                    res = Math.max(res, mergeSort(sum, 0, n, k, helper));
                }
                if (res == k) {
                    return k;
                }
            }
        }
        return res;
    }
    
    private int mergeSort(long[] sum, int start, int end , int k, long[] helper) {
        if (start >= end) {
            return Integer.MIN_VALUE;
        }
        int mid = start + (end - start) / 2;
        int left = mergeSort(sum, start, mid, k, helper);
        if (left == k) {
            return k;
        }
        int right = mergeSort(sum, mid + 1, end, k, helper);
        if (right == k) {
            return k;
        }
        int l = start, r = mid + 1;
        int p = start, t = mid + 1;
        System.arraycopy(sum, start, helper, start, end - start + 1);
        int res = Math.max(left, right);
        while (l <= mid) {
            while (t <= end && helper[t] - helper[l] <= k) {
                t++;
            }
            if (t - 1 > mid) {
                res = Math.max(res, (int) (helper[t - 1] - helper[l]));
                if (res == k) {
                    return k;
                }
            }
            while (r <= end && helper[r] < helper[l]) {
                sum[p++] = helper[r++];
            }
            sum[p++] = helper[l++];
        }
        return res;
    }
    // min = min(m, n), max = max(m, n)
    // Time = O(min ^ 2 * max*log(max))
    // Space = O(max + log(max))
}