class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = i > 0 ? dp[i - 1][j] + 1 : matrix[i][j] - '0';
                }
            }
        }
        
        int sum = 0;
        for (int i = 0; i < m; ++i) {
            int[] histogram = dp[i];
            sum = Math.max(sum, largestHistogram(histogram, n));
        }
        return sum;
    }
    
    private int largestHistogram(int[] array, int len) {
        int sum = 0;
        Deque<Integer> stack = new LinkedList<>();
        
        for (int i = 0; i <= len; ++i) {
            while (!stack.isEmpty() && (i == len || array[i] < array[stack.peekFirst()])) {
                int cur = stack.pollFirst();
                int pre = stack.isEmpty() ? -1 : stack.peekFirst();
                sum = Math.max(sum, (i - pre - 1) * array[cur]);
            }
            stack.offerFirst(i);
        }
        return sum;
        
    }

    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n)
}