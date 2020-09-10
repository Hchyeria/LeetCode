class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int n = matrix.length;
        flipUpAndBottom(matrix, n);
        
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                swap(matrix, i, j, j, i);
            }
        }
    }
    
    private void flipUpAndBottom(int[][] matrix, int n) {
        int mid = (n - 1) / 2;
        int end = n % 2 == 0 ? mid : mid - 1;
        for (int i = 0; i <= end; ++i) {
            for (int j = 0; j < n; ++j) {
                swap(matrix, i, j, n - 1 - i, j);
            }
        }
    }
    
    private void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int temp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = temp;
    }
    // Time = O(n^2)
    // Space = O(1)
}