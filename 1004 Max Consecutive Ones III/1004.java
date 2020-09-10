class Solution {
    public int longestOnes(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int zeros = 0;
        int res = 0;
        int left = 0, right = 0;
        while (right < A.length) {
            if (A[right] == 0) {
                zeros++;
            } 
            
            while (zeros > K) {
                zeros = A[left++] == 0 ? zeros - 1: zeros;
            }
            right++;
            res = Math.max(res, right - left);
        }
        return res;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}