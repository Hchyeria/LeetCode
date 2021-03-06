class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length, n = matrix[0].length;
        int start = 0, end = n * m - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int row = mid / n;
            int colum = mid % n;
            if (matrix[row][colum] == target) {
                return true;
            } else if (matrix[row][colum] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}