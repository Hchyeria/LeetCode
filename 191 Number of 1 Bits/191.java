public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>>= 1;
        }
        return count;
    }

    // n       110001000
    // n-1     110000111
    // n&(n-1) 110000000
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= n -1;
        }
        return count;
    }

}

// return Integer.bitCount(n)