class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums.length == 0) return res;
        res[0] = get_index(nums, target, true);
        res[1] = get_index(nums, target, false);
        return res;
    }

    public int get_index(int[] nums, int target, boolean isLeft) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target){
                start = mid + 1;
            } else if (nums[mid] > target){
                end = mid - 1;
            } else {
                if (isLeft) {
                    if (mid == 0 || nums[mid - 1] < target) {
                        return mid;
                    } else {
                        end = mid - 1;
                    }
                } else {
                    if (mid == nums.length - 1 || nums[mid + 1] > target) {
                        return mid;
                    } else {
                        start = mid + 1;
                    }
                }
            }
        }
        return -1;
    }
}