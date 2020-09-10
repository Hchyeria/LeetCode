/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canJump = function(nums) {
    if (!nums) {
        return false;
    }
    if (nums.length === 1) {
        return true;
    }
    let curMax = 0, n = nums.length
    for (let i = 0; i < n; ++i) {
        curMax = i <= curMax ? Math.max(curMax, i + nums[i]) : curMax
    }
    return curMax >= n - 1;
};