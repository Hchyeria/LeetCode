class Solution {
    // Solution 1: Hash Set
    public int missingNumber(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        for (int i = 0; i <= nums.length; ++i) {
            if (!hashSet.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    // Time Complexity: O(2n) -> O(n) in average, worse case O(n^2)
    // Space Complexity: O(n)

    // Solution 2: Bit XOR
    public int missingNumber2(int[] nums) {
        int miss = nums.length;
        for (int i = 0; i < nums.length; ++i) {
            miss ^= nums[i] ^ i;
        }
        return miss;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}