import java.util.Map;

class MyCalendar {
    private TreeMap<Integer, Integer> map;
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> low = map.lowerEntry(end);
        if (low != null && low.getValue() > start) {
            return false;
        }
        map.put(start, end);
        return true;
    }

    // Time Complexity: O(log(n))
    // Space Complexity: O(n)
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */