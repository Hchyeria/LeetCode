class Solution {
    private int m;
    private int n;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
                                         {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;
        n = board[0].length;
        int x = click[0], y = click[1];
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        } else {
            // B
            dfs(board, x, y);
        }
        return board;
    }
    
    private void dfs(char[][] board, int x, int y) {
        int mine = findMine(board, x, y);
        if (mine > 0) {
            board[x][y] = (char)('0' + mine);
            return;
        } else {
            board[x][y] = 'B';
            for (int[] d : dirs) {
                int i = x + d[0];
                int j = y + d[1];
                if (isArea(i, j) && board[i][j] == 'E') {
                    dfs(board, i, j);
                }
            }
        }
    }
    
    private int findMine(char[][] board, int x, int y) {
        int count = 0;
        for (int[] d : dirs) {
            int i = x + d[0];
            int j = y + d[1];
            if (isArea(i, j) && board[i][j] == 'M') {
                count++;
            }
        }
        return count;
    }
    
    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    // Time Complexity: O(Empty + 8*Empty)
    // Space Complexity: O(call-stack)
}