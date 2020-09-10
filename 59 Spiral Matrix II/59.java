class Solution {
    // Solution 1: iterative way
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if (n == 0) {
            return res;
        }
        int count = 1;
        int start = 0, end = n - 1;
        while (start < end) {
            for (int i = start; i < end; ++i) {
                res[start][i] = count++;
            }
            for (int i = start; i < end; ++i) {
                res[i][end] = count++;
            }
            for (int i = end; i > start; --i) {
                res[end][i] = count++;
            }
            for (int i = end; i > start; --i) {
                res[i][start] = count++;
            }
            start++;
            end--;
        }
        if (start == end) {
            res[start][end] = count;
        }
        return res;
    }
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    
    // Solution 2: recursion way
    public int[][] generateMatrix2(int n) {
        int[][] res = new int[n][n];
        if (n == 0) {
            return res;
        }
        int count = 1;
        int offset = 0;
        helper(res, offset, n, count);
        return res;
    }
    
    private void helper(int[][] res, int offset, int size, int count) {
        if (size == 0) {
            return;
        } else if (size == 1) {
            res[offset][offset] = count;
            return;
        }
        for (int i = 0; i < size - 1; ++i) {
            res[offset][offset + i] = count++;
        }
        for (int i = 0; i < size - 1; ++i) {
            res[offset + i][offset + size - 1] = count++;
        }
        for (int i = size - 1; i > 0; --i) {
            res[offset + size - 1][offset + i] = count++;
        }
        for (int i = size - 1; i > 0; --i) {
            res[offset + i][offset] = count++;
        }
        helper(res, offset + 1, size - 2, count);
    }
    
    // Time Complexity: O(n^2)
    // Space Complexity: O(n), call stack
}