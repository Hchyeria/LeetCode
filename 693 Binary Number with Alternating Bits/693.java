class Solution {
    public boolean hasAlternatingBits(int n) {
        while (n != 0) {
            if ((n & 1) == ((n >>> 1) & 1)) {
                return false;
            }
            n >>>= 1;
        }
        return true;
    }

    public boolean hasAlternatingBits2(int n) {
        if (n == 0) {
            return false;
        }
        int num = n ^ (n >> 1);
        return (num & (num + 1)) == 0;
    }
}