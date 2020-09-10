class Solution {
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true; // cause right start with num / 2
        if (num == Integer.MAX_VALUE) return false;
        int left = 1, right = num / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // need to prevent the overflow
            int multi = mid > (Integer.MAX_VALUE / mid) 
                ? Integer.MAX_VALUE
                : mid * mid;
            if (multi == num) {
                return true;
            } else if (multi < num) {
                left = mid + 1;
            } else {
                right =mid - 1;
            }
        }
        return false;
    }

    // Time = O(log(n))
    // Space = o(1)
}