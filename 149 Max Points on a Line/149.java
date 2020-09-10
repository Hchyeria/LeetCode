class Solution {
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int n = points.length;
        if (n == 1) {
            return 1;
        }
        int globalMax = 0;
        for (int i = 0; i < n; ++i) {
            int same = 1;
            int sameX = 0;
            Map<Double, Integer> map = new HashMap<>();
            int difference = 0;
            for (int j = 0; j < n; ++j) {
                if (j == i) {
                    continue;
                }
                int[] source = points[i];
                int[] next = points[j];
                if (source[0] == next[0] && source[1] == next[1]) {
                    same++;
                } else if (source[0] == next[0]) {
                    sameX++;
                } else {
                    // [[0,0],[94911151,94911150],[94911152,94911151]] test case, will cause precision problem
                    // replace with BigDecimal still has this problem
                    // double k = (double)(source[1] - next[1]) / (double)(source[0] - next[0]);

                    // replace the below version, find the gcd still has this problem
                    // so..... I give up
                    int dx = source[0] - next[0];
                    int dy = source[1] - next[1];
                    int gcd = getGcd(dx, dy);
                    if (gcd != 0) {
                        dx /= gcd;
                        dy /= gcd;
                    }
                    double k = (double)dy / dx;
                    int count = map.getOrDefault(k, 0);
                    map.put(k, count + 1);
                    difference = Math.max(difference, count + 1);
                }
                 
            }
            globalMax = Math.max(globalMax, Math.max(sameX, difference) + same);
        }
        return globalMax;
    }
    
    private int getGcd(int a, int b) {
        int r = 0;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
            
        }
        return r;
    }
    
}