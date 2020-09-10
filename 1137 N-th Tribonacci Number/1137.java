class Solution {
    public int tribonacci(int n) {
        if (n == 0) return 0;
        else if (n <= 2) return 1;
        
        int zero = 0, one = 1, two = 1;
        int res = 0;
        for (int i = 3; i <= n; ++i) {
            res = zero + one + two;
            zero = one;
            one = two;
            two = res;
        }
        return res;
    }
}