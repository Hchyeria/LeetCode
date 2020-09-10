class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length, n = matrix[0].length;
        
        int offset = 0;
        helper(res, matrix, m, n, offset);
        return res;
    }
    
    private void helper(List<Integer> res, int[][] matrix, int m, int n, int offset) {
        if (m * n <= 0) {
            return;
        } else if (m * n == 1) {
            res.add(matrix[offset][offset]);
            return;
        } else if (m == 1) {
            for (int i = 0; i < n; ++i) {
                res.add(matrix[offset][offset + i]);
            }
            return;
        } else if (n == 1) {
            for (int i = 0; i < m; ++i) {
                res.add(matrix[offset + i][offset]);
            }
            return;
        }
        for (int i = 0; i < n - 1; ++i) {
            res.add(matrix[offset][offset + i]);
        }
        for (int i = 0; i < m - 1; ++i) {
            res.add(matrix[offset + i][offset + n - 1]);
        }
        for (int i = n - 1; i > 0; --i) {
            res.add(matrix[offset + m - 1][offset + i]);
        }
        for (int i = m - 1; i > 0; --i) {
            res.add(matrix[offset + i][offset]);
        }
        helper(res, matrix, m - 2, n - 2, offset + 1);
    }

    // Time Complexity: O(n^m))
    // Space Complexity: O(min(m, n)), call stack
}