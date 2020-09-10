import java.util.HashMap;

class Solution {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            Integer index = map.get(nums[i]);
            if (index != null && i - index <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
    // Time = O(n)
    // Space = O(n)

    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    // Time = O(n)
    // Space = O(k)
}