class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        int count = 0;
        while (n > 0) {
            count += n & 1;
            // >>> logic shift: fill 0
            // >> arithmetic shift: fill sign number
            n >>>= 1;
        }
        return count == 1;
    }

    // n:  00010000
    // n - 1: 00001111
    // &      : 00000000

    // n:  01010000
    // n - 1: 01001111
    // &      : 01000000 not 0
    public boolean isPowerOfTwo2(int n) {
        // consider n <= 0!!
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
}