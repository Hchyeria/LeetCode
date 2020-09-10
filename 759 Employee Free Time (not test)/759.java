import java.util.Map;
import java.util.TreeMap;

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        if (schedule == null || schedule.size() == 0) {
            return res;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (List<Interval> s : schedule) {
            if (s == null || s.size() == 0) {
                continue;
            }
            map.put(s.get(0), map.getOrDefault(s.get(0), 0) + 1);
            map.put(s.get(1), map.getOrDefault(s.get(1), 0), - 1);
        }
        int start = -1;
        int cap = 0;
        for (Map<K,V>.Entry<Integer, Integer> entry : map.entrySet()) {
            cap += entry.getValue();
            if (cap == 0) {
                start = entry.getKey();
            }
            if (cap > 0 && start != -1) {
                res.add(new Interval(start, entry.getKey()));
                start = -1;
            }
        }
        return res;
    }
}