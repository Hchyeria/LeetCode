class Solution {
    // Solution 1: TreeMap
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null || newInterval.length == 0) {
            return intervals;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }
        map.put(newInterval[0], map.getOrDefault(newInterval[0], 0) + 1);
        map.put(newInterval[1], map.getOrDefault(newInterval[1], 0) - 1);
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
    // Time Complexity: O(n*log(n) + n)
    // Space Complexity: O(n)

    // Solution 2: sort
    // assumption: the intervals has been sorted before
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        if (newInterval == null || newInterval.length == 0) {
            return intervals;
        }
        // case1: no-overlap |_____|
        //                           |______| newInterval
        int l = 0;
        int n = intervals.length;
        List<int[]> res = new ArrayList<>();
        while (l < n && intervals[l][1] < newInterval[0]) {
            res.add(intervals[l++]);
        }

        // case2: overlap |______| |______|
        //                  |________| newInterval
        int start = newInterval[0], end = newInterval[1];
        while (l < n && intervals[l][0] <= newInterval[1]) {
            start = Math.min(intervals[l][0], start);
            end = Math.max(intervals[l++][1], end);
        }
        res.add(new int[] {start, end});
        
        // case3: non-overlap |______| newInterval
        //                              |_____|
        while (l < n) {
            res.add(intervals[l++]);
        }

        return res.toArray(new int[res.size()][]);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)
}