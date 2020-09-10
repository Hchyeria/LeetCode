class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<Integer>> res = nqueens2(n);
        List<List<String>> returnRes = new ArrayList<>();
        for (List<Integer> item : res) {
            List<String> solution = new ArrayList<>();
            for (int col : item) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; ++i) {
                    if (i != col) {
                        sb.append('.');
                    } else {
                        sb.append('Q');
                    }
                }
                solution.add(sb.toString());
            }
            returnRes.add(solution);
        }
        return returnRes;
    }
    
    // Solution 1:
    public List<List<Integer>> nqueens(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
        helper(res, new ArrayList<>(), 0, n);
        return res;
    }

    private void helper(List<List<Integer>> res, ArrayList<Integer> cur, int col, int n) {
        if (col == n) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0 ; i < n; ++i) {
            if (valid(cur, i)) {
                cur.add(i);
                helper(res, cur, col + 1, n);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean valid(List<Integer> cur, int i) {
        int index = 0;
        int newRow = cur.size();
        for (int row : cur) {
            if (row == i || Math.abs(row - i) == newRow - index) {
                return false;
            }
            index++;
        }
        return true;
    }

    // Time Complexity: O(n*n! * n), this is an upper bound, which is not tight
    // Space complexity: O(n)
    // O(n^n * n) -> optimized to O(n!)

    // Solution 2: validates queen's position in O(1) time
    public List<List<Integer>> nqueens2(int n) {
        List<List<Integer>> res = new ArrayList<>();
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

    private void helper2(List<List<Integer>> res, int[] cur, boolean[] usedCol,
                        boolean[] usedDiagonals, boolean[] usedReverseDiagonals, int n, int row) {
        if (row == n) {
            res.add(toList(cur));
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

    // Time complexity: O(n*n!), this is an upper bound, which is not tight
    // Space complexity: O(n)
}