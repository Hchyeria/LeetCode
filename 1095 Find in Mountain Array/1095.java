/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {

        int n = mountainArr.length();
        return helper(target, mountainArr, 0, n - 1);
        
    }
    
    private int helper(int target, MountainArray array, int left, int right) {
        // remember store the first left boundary
        int start = left;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int l = mid == 0 ? Integer.MIN_VALUE : array.get(mid - 1);
            int r = mid == right ? Integer.MIN_VALUE : array.get(mid + 1);
            int midValue = array.get(mid);
            if (midValue == target) {
                // here use the start, not helper(target, array, left, mid - 1);
                int beforeIndex = helper(target, array, start, mid - 1);
                if (beforeIndex == -1) {
                    return mid;
                } else {
                    return beforeIndex;
                }
            } else if (midValue > l && midValue > r) {
                if (target > midValue) return -1;
                int leftIndex = helper(target, array, left , mid - 1);
                if (leftIndex != -1) {
                    return leftIndex;
                }
                return helper(target, array, mid + 1, right);
            } else if ((target > midValue && r > l)
                    || (target < midValue && r < l)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // Time = O(log(n))
    // Space = O(1)
}