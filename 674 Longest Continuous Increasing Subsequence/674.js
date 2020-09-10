/**
 * @param {number[]} nums
 * @return {number}
 */
var findLengthOfLCIS = function(nums) {
    if (!nums) {
        return -1;
    }
    if (nums.length == 0) {
        return 0;
    }
    let cur = 1;
    let globalMax = 1
    let n = nums.length
    for (let i = 1; i < n; ++i) {
        if (nums[i] > nums[i - 1]) {
            cur++
            globalMax = Math.max(globalMax, cur)
        } else {
            cur = 1
        }
    }
    return globalMax;
};