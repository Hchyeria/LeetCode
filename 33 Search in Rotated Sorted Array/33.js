/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function(nums, target) {
    let start = 0
    let end = nums.length - 1
    // 又忘记边界条件了啊啊啊啊
    if (nums.length === 0) return -1; // check empty
    return binary_search(nums, target, start, end)
};

var binary_search = function(nums, target, start, end) {
    if (start > end) {
        return -1;
    }
    let mid = start + ((end - start) >> 1) // 这里必须要加括号
    if (nums[mid] === target) {
        // 频繁 return undefined ????
        // 明明 mid 有值的
        // woc 是因为 下面两个递归式前面都应该加 return
        return mid;
    } else if (nums[mid] >= nums[start]) {
        // 注意应该考虑 mid 也能等于 start 或者 end
        // 只需要考虑 在 mid 的左边还是右边 就够了
        // 因为你是以 mid 为分界的啊
        // 在左边的数据段中 只需要考虑满足左半边的条件 其他都在右半边
        if (nums[mid] > target && target >= nums[start]) {
            return binary_search(nums, target, start, mid - 1)
        } else {
            return binary_search(nums, target, mid + 1, end)
        }
        
    } else {
        // 不应该加上 nums[mid] < target 这个条件
        // 
        // if (nums[mid] < target && nums[end-1] >= target) {
        //    return binary_search(nums, target, mid + 1, end)
        // }
        // 如果是 nums = [4,5,6,7,0,1,2] target = 0
        // 那么 nums[mid] 刚好在分界线处 nums[mid] > target 但是 target 刚好在右边
        // 还有 为毛是 nums[end-1] end - 1 ???? 
        // 同理 右边段也是
        // 只需要考虑满足右半边的条件 其他都在左半边
        if (nums[mid] < target && nums[end] >= target) {
            return binary_search(nums, target, mid + 1, end)
        } else {
            return binary_search(nums, target, start, mid - 1)
        }
    }
}

/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function(nums, target) {
    if (nums.length === 0) return -1; // check empty

    let left = 0;
    let right = nums.length - 1;

    while (left <= right) {
        const mid = left + Math.floor((right - left) / 2);

        if (nums[mid] === target) return mid;

        // left sorted
        if (nums[left] <= nums[mid]) {
            // check if is in the left sorted part
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        // right sorted
        } else {
            // check if is in the right sorted part
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    return -1;
};