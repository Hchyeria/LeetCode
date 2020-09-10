class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int n = points.length;
        if (n == 1) {
            return 0;
        }
        
        int sum = 0;
        for (int i = 1; i < n; ++i) {
            sum += Math.max(Math.abs(points[i - 1][0] - points[i][0]), 
                            Math.abs(points[i - 1][1] - points[i][1]));
        }
        return sum;
    }
    // Time = O(n)
    // Space = O(1)
}