class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length <= 1) {
            return true;
        }
        int n = stones.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for (int stone : stones) {
            map.put(stone, new HashSet<>());
        }
        map.get(stones[0]).add(0);
        
        if (stones[1] - stones[0] > 1) {
            return false;
        }
        
        
        for (int i = 0; i < n; ++i) {
            Set<Integer> step = map.get(stones[i]);
            for (int k : step) {
                for (int j = k - 1; j <= k + 1; ++j) {
                    if (j <= 0) {
                        continue;
                    }
                    Set<Integer> next = map.get(stones[i] + j);
                    if (next != null) {
                        next.add(j);
                    }
                }
            }
        }
        return !map.get(stones[n - 1]).isEmpty();
        
    }

    // Time = O(n * k)
    // Space = O(n^2)
}