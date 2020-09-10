class Solution {
    public int[] singleNumber(int[] nums) {
        int x = 0;
        for (int i : nums) {
            x ^= i;
        }
        x &= -x; // get lowBit
        // x &= ~(x - 1); 
        // ~(x - 1) = - (x - 1) - 1 = -x
        // **..**10_00..00
        // after -1:
        // **..**01_11..11
        // hen we do ~ operation. The **..** part would all be reverted, and none of them would survive the upcoming & operation. 
        // 01 would become back 10, and would both survive the & operation, although the bit 1 is the only one we care about
        // then we use the last set bit to distinct two different number. Their bit of that position must can not be same
        int[] res = new int[2];
        for (int i : nums) {
            if ((i & x) == 0) {
                res[0] ^= i;
            } else {
                res[1] ^= i;
            }
        }
        return res;
    }

    // Time = O(n)
    // Space = O(1)
}