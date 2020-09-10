class Solution {
    private static class Point {
        int num;
        int weight;
        
        Point(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }
    public int networkDelayTime(int[][] times, int N, int K) {
        
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], k -> new HashMap<>())
                .put(time[1], time[2]);
        }
        
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pq.offer(new Point(K, 0));
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            res = p.weight;
            visited.add(p.num);
            if (visited.size() == N) {
                return res;
            }

            Map<Integer, Integer> map = graph.get(p.num);
            if (map != null) {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (!visited.contains(entry.getKey())) {
                        pq.offer(new Point(entry.getKey(), p.weight + entry.getValue()));
                    }

                }
            }    
        }
        return -1;
  
    }
    // Time = O(E*log(E)), Dijkstra's algorithm
    // Space = O(E)
}