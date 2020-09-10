class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            int sum = 0;
            for (int num : nums) {
                sum += (num >> i) & 1;
                sum %= 3;
            }
            res |= sum << i;
        }
        return res;
    }

    // Time = O(32 * n)
    // Space = O(1)

    // https://leetcode.com/problems/single-number-ii/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
    // k = 3, p = 1
    // m = 2, mask = ~(x2 & x1)
    public int singleNumber2(int[] nums) {
        int x1 = 0, x2 = 0, mask = 0;
        for (int num : nums) {
            x2 ^= x1 & num;
            x1 ^= num;
            mask = ~(x2 & x1);
            x2 &= mask;
            x1 &= mask;
        }
        return x1 | x2;
    }

    // Time = O(n * log(k))
    // Space = O(log(k))
    
}