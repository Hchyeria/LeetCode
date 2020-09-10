class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int v = citations[mid];
            int count = n - mid;
            if (count <= v) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        if (citations[left] >= n - left) {
            return n - left;
        }
        return 0;
    }

    // Time Complexity: O(log(n))
    // Space Complexity: O(1)
}