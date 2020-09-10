class Solution {
    public int totalNQueens(int n) {
        return nqueens2(n)[0];
    }
    
    public int[] nqueens2(int n) {
        int[] res = new int[1];
        if (n <= 0) {
            return res;
        }
        int[] cur = new int[n];
        boolean[] usedCol = new boolean[n];
        boolean[] usedDiagonals = new boolean[2 * n - 1];
        boolean[] usedReverseDiagonals = new boolean[2 * n - 1];
        helper2(res, cur, usedCol, usedDiagonals, usedReverseDiagonals, n, 0);
        return res;
    }

    private void helper2(int[] res, int[] cur, boolean[] usedCol,
                         boolean[] usedDiagonals, boolean[] usedReverseDiagonals, int n, int row) {
        if (row == n) {
            res[0]++;
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (valid2(row, i, usedCol, usedDiagonals, usedReverseDiagonals, n)) {
                cur[row] = i;
                flip(row, i, usedCol, usedDiagonals, usedReverseDiagonals, n);
                helper2(res, cur, usedCol, usedDiagonals, usedReverseDiagonals, n, row + 1);
                flip(row, i, usedCol, usedDiagonals, usedReverseDiagonals, n);
            }
        }
    }

    private void flip(int row, int col, boolean[] usedCol, boolean[] usedDiagonals,
                      boolean[] usedReverseDiagonals, int n) {
        usedCol[col] = !usedCol[col];
        usedDiagonals[col + row] = !usedDiagonals[col + row];
        usedReverseDiagonals[n - 1 - row + col] = !usedReverseDiagonals[n - 1 - row + col];
    }

    private boolean valid2(int row, int col, boolean[] usedCol, boolean[] usedDiagonals,
                           boolean[] usedReverseDiagonals, int n) {
        return !usedCol[col] && !usedDiagonals[row + col] && !usedReverseDiagonals[n - 1 - row + col];
    }

    private List<Integer> toList(int[] data) {
        List<Integer> res = new ArrayList<>();
        for (int datum : data) {
            res.add(datum);
        }
        return res;
    }
}