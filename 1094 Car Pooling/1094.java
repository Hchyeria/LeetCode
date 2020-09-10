import java.lang.reflect.Array;

class Solution {
    // Solution 1: sort + pq
    public boolean carPooling3(int[][] trips, int capacity) {
        Arrays.sort(trips, Comparator.comparing(trip -> trip[1]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(trip -> trip[2]));
        for (int[] trip : trips) {
            while (!pq.isEmpty() && trip[1] >= pq.peek()[2]) {
                capacity += pq.poll()[0];
            }
            capacity -= trip[0];
            if (capacity < 0) {
                return false;
            }
            pq.offer(trip);
        }
        return true;
    }

    // Time Complexity: O(nlog(n))
    // Space Complexity: O(n)
    
    // Solution 2: TreeMap
    public boolean carPooling(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) + trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) - trip[0]);
        }
        int sum = 0;
        for (Integer value : map.values()) {
            sum += value;
            if (sum > capacity) {
                return false;
            }
        }
        return true;
    }

    // Time Complexity: O(nlog(n))
    // Space Complexity: O(n)

    // Solution 3: Array
    public boolean carPooling2(int[][] trips, int capacity) {
        int[] stops = new int[1001];
        for (int[] trip : trips) {
            stops[trip[1]] += trip[0];
            stops[trip[2]] -= trip[0];
        }
        for (int v : stops) {
            capacity -= v;
            if (capacity < 0) {
                return false;
            }
        }
        return true;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1001)
}