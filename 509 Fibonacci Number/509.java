class Solution {
    public int fib(int N) {
        int a = 0;
        int b = 1;
        for (int i = 0; i < N; ++i) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;
    }
}