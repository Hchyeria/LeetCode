class Solution {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m, n;
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) {
            return false;
        }
        m = board.length;
        n = board[0].length;
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, String s, int i, int j, int len) {
        char c = board[i][j];
        if (s.charAt(len) != c) {
            return false;
        }
        // careful we can't use (len==s.length) at the base case at start
        // because the graph will check the valid x, y before come in this function
        // [["a"]] "a", test case will fail
        len++;
        if (len == s.length()) {
            return true;
        }
        board[i][j] = '#';
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (isArea(x, y) && board[x][y] != '#') {
                if (dfs(board, s, x, y, len)) {
                    return true;
                }
            }
        }
        board[i][j] = c;
        return false;
    }
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    // Time = O(m*n * 4^s.length())
    // Space = O(s.length())
}