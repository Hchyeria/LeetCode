// the same as LC1296

class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null || hand.length % W != 0) {
            return false;
        }
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        int lastNum = -1, opened = 0, count = 0;
        Queue<Integer> q = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            if ((opened > 0 && key > lastNum + 1) || opened > val) return false;
            q.offer(val - opened);
            lastNum = key;
            opened = val;
            if (q.size() >= W) {
                int t = q.poll();
                // only t != 0, then we can add 1 to count
                // besides we can judge by hand.length == W * W, that can find the whether the count == W
                if (t != 0) count++;
                opened -= t;
            }
        }
        System.out.println(count);
        return opened == 0;
    }

    // Time = O(n*log(n) + n)
    // Space = O(n + W)
}