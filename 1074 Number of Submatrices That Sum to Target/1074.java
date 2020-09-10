class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        if (matrix == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] += matrix[i - 1][j];
            }
        }
        int res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = i; j < m; ++j) {
                count.clear();
                count.put(0, 1);
                int sum = 0;
                for (int k = 0; k < n; ++k) {
                    sum += i > 0 ? matrix[j][k] - matrix[i - 1][k] : matrix[j][k];
                    int c = count.getOrDefault(sum - target, 0);
                    res += c;
                    count.put(sum, count.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return res;
    }

    // Time = O(M^2 * N), actually we choose min(m, n)^n * max(m, n)
    // Space = O(N)
}