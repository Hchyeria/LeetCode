// LintCode 920. Meeting Rooms

class Solution {
    //  Solution 1: sort
    public boolean canAttendMeetings(List<Interval> intervals) {
        // Write your code here
        if (intervals == null || intervals.size() == 0) {
            return true;
        }
        Collections.sort(intervals, (i1, i2) -> Integer.compare(i1.start, i2.start));
        Interval pre = null;
        for (Interval cur : intervals) {
            if (pre != null && cur.start < pre.end) {
                return false;
            }
            pre = cur;
        }
        return true;
    }
    // Time Complexity: O(n*log(n))
    // Space Complexity: O(1)

    // Solution 2: TreeMap
    public boolean canAttendMeetings2(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return true;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Interval pre = intervals.get(0);
        for (Interval cur : intervals) {
            map.put(cur.start, map.getOrDefault(cur.start, 0) + 1);
            map.put(cur.end, map.getOrDefault(cur.end, 0) - 1);
        }
        int cap = 1;
        for (int v : map.values()) {
            cap -= v;
            if (cap < 0) {
                return false;
            }
        }
        return true;
    }
    // Time Complexity: O(n*log(n) + n)
    // Space Complexity: O(n)

}