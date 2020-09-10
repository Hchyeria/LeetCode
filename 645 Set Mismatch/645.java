class Solution {

    // Solution 1: hashSet + XOR
    // Time = O(n), Space = O(n) 
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        
        Set<Integer> set = new HashSet<>();
        int repetition = 0;
        for (int num : nums) {
            if (!set.add(num)) {
                repetition = num;
                break;
            }
        }
        int x = 0;
        for (int i = 1; i <= n; ++i) {
            x ^= i ^ nums[i - 1];
        }
        
        return new int[] {repetition, x ^ repetition};
    }

    // Solution 1: use the index and value in array
    // Time = O(n), Space = O(1) 
    public int[] findErrorNums2(int[] nums) {
        int n = nums.length;
        int[] res = new int[2];
        
        for (int num : nums) {
            int i = Math.abs(num);
            if (nums[i - 1] > 0) {
                nums[i - 1] = -nums[i - 1];
            } else {
                res[0] = i;
            }
        }
        
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                res[1] = i + 1;
                break;
            }
        }
        return res;
    }
}