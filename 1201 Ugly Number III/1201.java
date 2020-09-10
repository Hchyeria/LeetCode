class Solution {
    // TLE
    public int nthUglyNumber(int n, int a, int b, int c) {
        int ai = 1, bi = 1, ci = 1;
        int next = 1;
        for (int i = 0; i < n; ++i) {
            next = Math.min(ai * a, Math.min(bi * b, ci * c));
            
            if (next == ai * a) {
                ai++;
            }
            if (next == bi* b) {
                bi++;
            }
            if (next == ci * c) {
                ci++;
            }
        }
        return next;
    }

    // Math
    private long gcd(long a, long b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    int count(long num, long a, long b, long c) {
        return (int) (num / a + num / b + num / c
                - num / lcm(a, b)
                - num / lcm(b, c)
                - num / lcm(a, c)
                + num / (lcm(a, lcm(b, c))));
    }

    public int nthUglyNumber2(int n, int a, int b, int c) {
        int left = 0, right = (int) 2e9, result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (count(mid, a, b, c) >= n) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
    // Time = O(log(right))
    // Space = O(1)
}