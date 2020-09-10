import java.util.Map;
import java.util.TreeMap;

class Solution {
    // Solution 1: sort
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, Comparator.comparing(interval -> interval[0]));
        List<int[]> res = new ArrayList<>();
        int preStart = intervals[0][0], preEnd = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            if (start <= preEnd) {
                preEnd = Math.max(preEnd, end);
            } else {
                res.add(new int[] {preStart, preEnd});
                preStart = start;
                preEnd = end;
            }
        }
        // remember add the last one
        res.add(new int[] {preStart, preEnd});
        return res.toArray(new int[res.size()][]);
    }

    // Time Complexity: O(n*log(n) + n + n)
    // Space Complexity: O(n)

    // Solution 2: this solution is twice faster than the solution 1
    public int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        // if intervals is List, we can uss
        // intervals.sort(Comparator.comparing(i -> i.start))
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        List<int[]> res = new ArrayList<>();
        int[] pre = intervals[0];
        res.add(pre);
        for (int[] interval : intervals) {
            // [[1,4],[4,5]] -> [1, 5]
            if (interval[0] <= pre[1]) {
                pre[1] = Math.max(pre[1], interval[1]);
            } else {
                pre = interval;
                res.add(pre);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    // Time Complexity: O(n*log(n))
    // Space Complexity: O(n)

    // Follow up: what if the input is a unlimited large stream?
    // use TreeMap
    // lowerKey() return less than the given key
    // floorKey() return less or equal than the given key
    public int[][] merge3(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }
        List<int[]> res = new ArrayList<>();
        int cap = 0, pre = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (cap == 0) {
                pre = entry.getKey();
            } 
            cap += entry.getValue();
            if (cap == 0) {
                res.add(new int[] {pre, entry.getKey()});
            }
        }

        return res.toArray(new int[res.size()][]);
    }
    // Time Complexity: O(n*log(n))
    // Space Complexity: O(n)

}