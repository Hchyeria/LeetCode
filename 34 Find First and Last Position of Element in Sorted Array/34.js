/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var searchRange = function(nums, target) {
    let length = nums.length
    if (!length) return [-1, -1];
    return [get_index(nums, target, 0, length-1, true), get_index(nums, target, 0, length-1, false)]
};

var get_index = function(nums, target, start, end, isLeft) {
    if (start > end) {
        return -1;
    }
    let mid = start + ((end - start) >> 1)
    if (isLeft) {
        if (nums[mid] == target && (mid == 0 || nums[mid-1] < target)) {
            return mid;
        } else if (nums[mid] < target) {
            return get_index(nums, target, mid + 1, end, isLeft);
        } else {
            return get_index(nums, target, start, mid - 1, isLeft);
        }
    } else {
        if (nums[mid] == target && (mid == end || nums[mid+1] > target)) {
            return mid;
        } else if (nums[mid] > target) {
            return get_index(nums, target, start, mid - 1, isLeft);
        } else {
            return get_index(nums, target, mid + 1, end, isLeft);
        } 
    }
}