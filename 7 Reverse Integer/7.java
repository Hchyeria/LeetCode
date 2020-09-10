class Solution {
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10;
            int newRes = result * 10 + tail;
            if ((newRes - tail) / 10 != result) {
                return 0;
            }
            result = newRes;
            x /= 10;
        }
        return result;
    }
}