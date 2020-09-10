class Solution {
    // Solution 1: TreeMap
    public int[] corpFlightBookings(int[][] bookings, int n) {
        if (bookings == null || bookings.length == 0) {
            return new int[0];
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] booking : bookings) {
            map.put(booking[0], map.getOrDefault(booking[0], 0) + booking[2]);
            map.put(booking[1] + 1, map.getOrDefault(booking[1] + 1, 0) - booking[2]);
        }
        
        int[] res = new int[n];
        int cap = 0;
        int pre = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            // or use the floorKey()
            int index = entry.getKey() - 1;
            Arrays.fill(res, pre, index, cap);
            cap += entry.getValue();
            pre = index;
        }
        return res;
    }
}