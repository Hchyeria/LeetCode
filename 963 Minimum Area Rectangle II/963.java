class Solution {
    public double minAreaFreeRect(int[][] points) {
        double res = Double.MAX_VALUE;
        if (points == null || points.length < 4) {
            return 0.0;
        }
        Map<String, List<int[]>> map = new HashMap<>();
        int n = points.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                double dis = distance(points[i], points[j]);
                sb.setLength(0);
                double centX = points[i][0] + ((double) points[j][0] - points[i][0]) / 2;
                double centY = points[i][1] + ((double) points[j][1] - points[i][1]) / 2;
                String index = sb.append(dis).append(centX).append(centY).toString();
                map.computeIfAbsent(index, (k) -> new ArrayList<>())
                    .add(new int[] {i, j});
            }
        }
        
        for (List<int[]> key : map.values()) {
            if (key.size() <= 1) {
                continue;
            }
            int size = key.size();
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < i; ++j) {
                    int[] p1 = points[key.get(i)[0]];
                    int[] p2 = points[key.get(j)[0]];
                    int[] p3 = points[key.get(j)[1]];
                    res = Math.min(res, getArea(p1, p2, p3));
                }
            }
        }
        return Double.compare(res, Double.MAX_VALUE) == 0 ? 0.0 : res;
    }
    
    private double distance(int[] p1, int[] p2) {
        return Math.sqrt((1l * p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));
    }
    
    private double getArea(int[] p1, int[] p2, int[] p3) {
        return distance(p1, p2) * distance(p1, p3);
    }

    // Time = O(n ^ 2)
    // Space = O(n ^ 2)
}