class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] helper = new int[n];
        return mergeSort(nums, 0, n - 1, helper);
    }
    
    private int mergeSort(int[] nums, int start, int end, int[] helper) {
        if (start >= end) {
            return 0;
        }
        int res  = 0;
        int mid = start + (end - start) / 2;
        res += mergeSort(nums, start, mid, helper);
        res += mergeSort(nums, mid + 1, end, helper);
        System.arraycopy(nums, start, helper, start, end - start + 1);
        int l = start, r = mid + 1;
        int p = start, t = mid + 1;
        long temp = 0L; // 2 * helper[t] maybe overflow
        while (l <= mid) {
            // can't write like this
            // int t = end;
            // while (t > mid && ((temp = 2L * helper[t]) >= helper[l])) {
            //     t--;
            // }
            // res += t - mid;
            // because t is monotonic changed, during while loop
            // if we use t--, we should initial t as end every loop
            // the time will be n^2, will cause TLE
            while (t <= end && ((temp = 2L * helper[t]) < helper[l])) {
                t++;
            }
            if (t > mid + 1) {
                res += t - (mid + 1);
            }
            while (r <= end && helper[r] < helper[l]) {
                nums[p++] = helper[r++];
            }
            nums[p++] = helper[l++];
        }
        return res;
    }
    // Time = O(n * log(n))
    // Space = O(n + log(n))
}