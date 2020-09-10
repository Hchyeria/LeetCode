class Solution {
    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        if (n == 1) {
            return arr[0][0];
        }
        
        int[][] dp =new int[n][n];
        
        int min1 = 0, min2 = 0;
        int id = -1;
        
        for (int i = 0; i < n; ++i) {
            int curMin1 = Integer.MAX_VALUE, curMin2 = Integer.MAX_VALUE;
            int curId = -1;
            for (int j = 0; j < n; ++j) {
                int cur = 0;
                if (j != id) {
                    cur = min1 + arr[i][j];
                } else {
                    cur = min2 + arr[i][j];
                }
                
                if (cur < curMin1) {
                    curMin2 = curMin1;
                    curId = j;
                    curMin1 = cur;
                } else if (cur < curMin2) {
                    curMin2 = cur;
                }
            }
            min1 = curMin1;
            id = curId;
            min2 = curMin2;
        }
        
        return min1;
    }

    // Time = O(m * n), the same as paint house 2
    // Space = O(1)
}