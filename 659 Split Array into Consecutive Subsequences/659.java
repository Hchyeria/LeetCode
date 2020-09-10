class Solution {
    // Greedy
    public boolean isPossible(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> end = new HashMap<>();
        
        for (int num : nums) {
            left.put(num, left.getOrDefault(num, 0) + 1);
        }
        
        for (int num : nums) {
            Integer i = 0, j = 0;
            if ((i = left.get(num)) <= 0) {
                continue;
            }
            
            left.put(num, i - 1);
            
            if ((i = end.get(num - 1)) != null && i > 0) {
                end.put(num - 1, i - 1);
                end.put(num, end.getOrDefault(num, 0) + 1);
            } else if ((i = left.get(num + 1)) != null && i > 0 
                       && (j = left.get(num + 2)) != null && j > 0) {
                left.put(num + 1, i - 1);
                left.put(num + 2, j - 1);
                end.put(num + 2, end.getOrDefault(num + 2, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }

    // Time = O(n)
    // Space = O(n)
}
