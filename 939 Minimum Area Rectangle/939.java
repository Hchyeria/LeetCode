class Solution {
    public int minAreaRect(int[][] points) {
        if (points == null || points.length < 4) {
            return 0;
        }
        int max = 40000;
        int res = Integer.MAX_VALUE;
        TreeMap<Integer, List<Integer>> pointMap = new TreeMap<>();
        
        for (int[] p : points) {
            pointMap.computeIfAbsent(p[0], (k) -> new ArrayList<>())
                .add(p[1]);
        }
        Map<Long, Integer> visited = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : pointMap.entrySet()) {
            int x2 = entry.getKey();
            List<Integer> ys = entry.getValue();
            Collections.sort(ys);
            int n = ys.size();
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < i; ++j) {
                    if (j < n - 1 && ys.get(j) == ys.get(j + 1)) {
                        continue;
                    }
                    long index = (long) ys.get(j) * max + ys.get(i);
                    Integer x1 = visited.get(index);
                    if (x1 != null) {
                        res = Math.min(res, (ys.get(i) - ys.get(j)) * (x2 - x1));
                    }
                    visited.put(index, x2);
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    // Time = O(n ^ 2)
    // Space = O(n * 2)
}