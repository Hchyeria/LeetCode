/**
 * @param {number[]} nums
 * @param {number} target
 * @return {boolean}
 */
var search = function(nums, target) {
    let start = 0
    let end = nums.length - 1
    if (nums.length === 0) return false; // check empty
    // 这里又尼玛写成大于0了？？？ why not exactly at index 0 position?
    if (binary_search(nums, target, start, end) >= 0){
        return true;
    }
    
    return false;
};

var binary_search = function(nums, target, start, end) {
    if (start > end) {
        return -1;
    }
    let mid = start + ((end - start) >> 1)
    if (nums[mid] === target) {
        return mid;
    } else if (nums[mid] > nums[start]) {
        if (nums[mid] > target && target >= nums[start]) {
            return binary_search(nums, target, start, mid - 1)
        } else {
            return binary_search(nums, target, mid + 1, end)
        }
        
    } else if (nums[mid] < nums[start]) {
        if (nums[mid] < target && nums[end] >= target) {
            return binary_search(nums, target, mid + 1, end)
        } else {
            return binary_search(nums, target, start, mid - 1)
        }
    } else {
        return binary_search(nums, target, ++start, end)
    }
}