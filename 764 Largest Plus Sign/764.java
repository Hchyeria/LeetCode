class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] matrix = new int[N][N];
        for (int i = 0 ; i < N; ++i) {
            Arrays.fill(matrix[i], 1);
        }
        
        for (int[] p : mines) {
            matrix[p[0]][p[1]] = 0;
        }
        List<int[][]> upLeft = buildUpLeft(matrix, N);
        List<int[][]> bottomRight = buildBottomRight(matrix, N);
        int[][] up = upLeft.get(0);
        int[][] left = upLeft.get(1);
        int[][] bottom = bottomRight.get(0);
        int[][] right = bottomRight.get(1);
        int res = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (matrix[i][j] == 1) {
                    res = Math.max(res, 
                                   min(new int[] {up[i][j], bottom[i][j], left[i][j], right[i][j]}));
                }
            }
        }
        return res;
    }
    
    private List<int[][]> buildUpLeft(int[][] matrix, int n) {
        int[][] up = new int[n][n];
        int[][] left = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 1) {
                    up[i][j] = i == 0 ? 1 : up[i - 1][j] + 1;
                    left[i][j] = j == 0 ? 1 : left[i][j - 1] + 1;
                }
            }
        }
        List<int[][]> res = new ArrayList<>();
        res.add(up);
        res.add(left);
        return res;
    }
    
    private List<int[][]> buildBottomRight(int[][] matrix, int n) {
        int[][] bottom = new int[n][n];
        int[][] right = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (matrix[i][j] == 1) {
                    bottom[i][j] = i == n - 1 ? 1 : bottom[i + 1][j] + 1;
                    right[i][j] = j == n - 1 ? 1 : right[i][j + 1] + 1; 
                }
            }
        }
        List<int[][]> res = new ArrayList<>();
        res.add(bottom);
        res.add(right);
        return res;
    }
    
    private int min(int[] nums) {
        int res = nums[0];
        for (int i : nums) {
            res = Math.min(res, i);
        }
        return res;
    }

    // Time = O(N ^ 2)
    // Space = O(N ^ 2)
}