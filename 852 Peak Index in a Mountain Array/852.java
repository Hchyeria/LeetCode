class Solution {
    public int peakIndexInMountainArray(int[] A) {
        int n = A.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int left = mid == 0 ? Integer.MIN_VALUE : A[mid - 1];
            int right = mid == n - 1 ? Integer.MIN_VALUE : A[mid + 1];
            if (A[mid] > left && A[mid] > right) {
                return mid;
            } else if (left > A[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // Time = O(log(n))
    // Space = O(1)
}