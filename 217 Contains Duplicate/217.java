class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Set<Integer>  visited = new HashSet<>();
        for (int i : nums) {
            if (!visited.add(i)) {
                return true;
            }
        }
        return false;
    }
    // Time = O(n)
    // Space = O(n)
}