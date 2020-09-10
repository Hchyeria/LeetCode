class Solution {
    public boolean isUgly(int num) {
        final int[] prime = {2, 3, 5};
        for (int p : prime) {
            while (num > 0 && num % p == 0) {
                num /= p;
            }
        }
        return num == 1;
    }
    // Time = O(num)
    // Space = O(1)
}