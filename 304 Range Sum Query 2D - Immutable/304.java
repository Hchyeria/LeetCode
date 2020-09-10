class NumMatrix {

    private int[][] sum;
    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        if (n == 0) return;
        sum = new int[m][n];
        
        for (int i = 0; i < m; ++i) {
            int total = 0;
            for (int j = 0; j < n; ++j) {
                total += matrix[i][j];
                sum[i][j] = total;
                if (i > 0) {
                    sum[i][j] += sum[i - 1][j];
                }
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int up = row1 == 0 ? 0 : sum[row1 - 1][col2];
        int left = col1 == 0 ? 0 : sum[row2][col1 - 1];
        int same = row1 == 0 || col1 == 0 ? 0 : sum[row1 - 1][col1 - 1];
        return sum[row2][col2] - up - left + same;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */