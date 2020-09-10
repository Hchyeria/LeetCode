from queue import PriorityQueue
class Solution:
    def minMeetingRooms(self, intervals: List[Interval], target: int) ->int:
        intervals.sort()
        pq = PriorityQueue()
        for item in intervals:
            pq.put(item.end, item)
        for i in range(1, len(intervals)):
            pre = pq.get()[1]
            cur = intervals[i]
            if pre.end > cur.start:
                pq.put(cur.end, cur)
            else:
                pre.end = cur.end
            pq.put(pre.end, pre)
        return len(pq)