// 静态内部类 它不能使用外部类的非static成员变量或者方法
/* public class Test {
    public static void main(String[] args)  {
        Outter.Inner inner = new Outter.Inner();
    }
} */

// LintCode 919. Meeting Rooms II

class Solution {
    // Solution 1: sort + pq
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
             return 0;
         }
         Collections.sort(intervals, new Comparator<Interval>() {
             public int compare(Interval a, Interval b) {
                 return a.start - b.start;
             }
         });
         // PriorityQueue
         // sorted by the end
         PriorityQueue<Interval> queue = new PriorityQueue<>(intervals.size(), (a, b) -> a.end - b.end);
         queue.offer(intervals.get(0));
         for (int i = 1; i < intervals.size(); ++i) {
             Interval pre = queue.poll();
             Interval cur = intervals.get(i);
             // if overlap add a new room
             if (pre.end > cur.start) {
                 queue.offer(cur);
             } else {
                 // update the end time to the later conference
                 pre.end = cur.end;
             }
             queue.offer(pre);
         }
         return queue.size();
     }

    // Time Complexity: O(nlog(n))
    // Space Complexity: O(n) worse case

    // Solution 2: TreeMap
    public int minMeetingRooms2(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return 0;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (Interval interval : intervals) {
            map.put(interval.start, map.getOrDefault(interval.start, 0) + 1);
            map.put(interval.end, map.getOrDefault(interval.end, 0) - 1);
        }
        int capacity = 0, res = 0;
        for (Integer v : map.values()) {
            capacity += v;
            res = Math.max(res, capacity);
        }
        return res;
    }

    // Time Complexity: O(nlog(n))
    // Space Complexity: O(n) worse case
}



