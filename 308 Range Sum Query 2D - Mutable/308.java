class NumMatrix {

    private int[][] A;
    private long[][] tree;

    private void add(int row, int col, int val) {
        int m = tree.length;
        int n = tree[0].length;
        int i = row;
        while (i < m) {
            // need to reset j to col at each loop
            int j = col;
            while (j < n) {
                tree[i][j] += val;
                j += (j & -j);
            }
            i += (i & -i);
        }
    }

    private long query(int row, int col) {
        long sum = 0;
        int i = row;
        while (i > 0) {
            // need to reset j to col at each loop
            int j = col;
            while (j > 0) {
                sum += tree[i][j];
                j -= (j & -j);
            }
            i -= (i & -i);
        }
        return sum;
    }

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        A = new int[m][n];
        tree = new long[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                add(i + 1, j + 1, matrix[i][j]);
                A[i][j] = matrix[i][j];
            }
        }
    }

    public void update(int row, int col, int val) {
        add(row + 1, col + 1, val - A[row][col]);
        A[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        long up = row1 == 0 ? 0 : query(row1, col2 + 1);
        long left = col1 == 0 ? 0 : query(row2 + 1, col1);
        long same = row1 == 0 || col1 == 0 ? 0 : query(row1, col1);
        return (int) (query(row2 + 1, col2 + 1) - up - left + same);
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */