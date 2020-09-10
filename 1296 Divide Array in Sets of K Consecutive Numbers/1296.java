// the same as LC846 Hand of Straights

class Solution {
    // https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/discuss/470238/JavaC%2B%2BPython-Exactly-Same-as-846.-Hand-of-Straights
    // Solution 1: when k isn't big
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums == null || nums.length % k != 0) {
            return false;
        }
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() <= 0) {
                continue;
            }
            int key = entry.getKey();
            for (int i = k - 1; i >= 0; --i) {
                if (map.getOrDefault(key + i, 0) < entry.getValue()) return false;
                map.put(key + i, map.getOrDefault(key + i, 0) - entry.getValue());
            }
        }
        return true;
    }

    // Time = O(n*log(n) + n*k)
    // Space = O(n)
    
    // Follow up: when k ~= n
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums == null || nums.length % k != 0) {
            return false;
        }
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            // map.merge(num, 1, Integer::sum);
        }
        
        int lastNum = 0, opened = 0;
        Queue<Integer> needToClose = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            if ((opened > 0 && key > lastNum + 1) || opened > val) return false;
            needToClose.offer(val - opened);
            lastNum = key;
            opened = val;
            if (needToClose.size() >= k) {
                opened -= needToClose.poll();
            }
        }
        return opened == 0;
    }
    // Time = O(n*log(n) + n)
    // Space = O(n + k)
}