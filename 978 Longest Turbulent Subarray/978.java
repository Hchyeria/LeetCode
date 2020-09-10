class Solution {

    // Solution 1: Two Pointer
    public int maxTurbulenceSize(int[] A) {
        if (A == null) return 0;
        int n = A.length;
        if (n == 1) return 1;

        int res = 1;
        boolean preIsLarge = A[1] - A[0] > 0;
        int left = 0;
        for (int i = 1; i < n; ++i) {
            boolean isLarge = A[i] - A[i - 1] > 0;
            if (A[i] == A[i - 1]) {
                left = i;
            } else if (i != 1 && isLarge == preIsLarge) {
                left = i - 1;
            } else {
                res = Math.max(res, i - left + 1);
            }
            preIsLarge = isLarge;
        }
        return res;
    }

    // Time = O(n)
    // Space = O(1)

    // Solution 2: DP
    // dp[i] = dp[i - 1] + 1, if A[i] - A[i - 1] > 0 != A[i - 1] - A[i - 2] > 0 && pre > 1
    // dp[i] = 1, if A[i] == A[i - 1]
    // dp[i] = 2, otherwise
    public int maxTurbulenceSize2(int[] A) {
        if (A == null) return 0;
        int n = A.length;
        if (n == 1) return 1;

        int res = 1;
        int pre = 1;
        
        for (int i = 1; i < n; ++i) {
            if (A[i] == A[i - 1]) {
                pre = 1;
            } else if (pre > 1 && i > 1) {
                boolean b1 = A[i] - A[i - 1] > 0;
                boolean b2 = A[i - 1] - A[i - 2] > 0;
                if (b1 != b2) {
                    pre += 1;
                } else {
                    pre = 2;
                }
            } else {
                pre = 2;
            }

            res = Math.max(res, pre);
        }
        return res;
    }

    // Time = O(n)
    // Space =o(1)
}