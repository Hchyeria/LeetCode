class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int res = 0;
        
        while (left < right) {
            int l = height[left];
            int r = height[right];
            res = Math.max(res, Math.min(l, r) * (right - left));
            if (l <= r) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    // Time = O(n)
    // Space = O(1)

}