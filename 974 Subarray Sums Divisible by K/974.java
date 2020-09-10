class Solution {
    public int subarraysDivByK(int[] A, int K){
        long prefix = 0L;
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        int count = 0;
        for (int i : A) {
            // when prefix < 0
            prefix += i;
            prefix = ((prefix % K) + K) % K;
            Integer c = map.get(prefix);
            if (c != null) {
                count += c;
            }
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }
        return count;
    }
    // Time = O(n)
    // Space = O(n)
}