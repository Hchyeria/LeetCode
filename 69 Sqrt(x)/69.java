class Solution {
    public int mySqrt(int x) {
            if (x == 0 || x == 1) {
                return x;
            }
    
            int left = 1;
            int right = x;
    
            while (left < right - 1) {
                int mid = left + (right - left) / 2;
                // use long to avoid overflow
                long power = (long) mid * mid;
                if (power == x) {
                    return mid;
                } else if (power < x) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            long rightPower = (long) right * right;
            if (rightPower <= x) {
                return right;
            } else {
                return left;
            }
    
        }
    }

    public int mySqrt2(int x) {
        if (x == 1 || x == 0) return x;
        int left = 1, right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long mul = (long) mid * mid;
            if (mul == x) {
                return mid;
            } else if (mul < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}