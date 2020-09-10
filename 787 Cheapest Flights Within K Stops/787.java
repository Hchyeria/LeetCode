import java.util.Map;
import java.util.Set;

class Solution {
    private static class Point {
        int num;
        int weight;
        int k;
        
        Point(int num, int weight, int k) {
            this.num = num;
            this.k = k;
            this.weight = weight;
        }
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], k -> new HashMap<>())
                .put(flight[1], flight[2]);
        }
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pq.offer(new Point(src, 0, 0));
        Set<Integer> visited = new HashSet<>();
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (p.num == dst) {
                return p.weight;
            }
            visited.add(p.num);
            if (p.k <= K) {
                Map<Integer, Integer> map = graph.get(p.num);
                if (map != null) {
                    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                        if (!visited.contains(entry.getKey())) {
                            pq.offer(new Point(entry.getKey(), p.weight + entry.getValue(), p.k + 1));
                        }
                        
                    }
                }
                
            }
        }
        return -1;
        
    }

    // Time = O(E*log(E)), Dijkstra's algorithm
    // Space = O(E)
    // add distance array doesn't work, why??
    // int[] dis = new int[n];
    // Arrays.fill(dis, Integer.MAX_VALUE);
}
