/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function(nums) {
    let n = nums.length;
    if (n === 1) {
        return nums[0];
    }
    let preSum = 0
    let globalMax = -Infinity
    for (let i = 0; i < n; ++i) {
        preSum = Math.max(preSum + nums[i], nums[i])
        globalMax = Math.max(globalMax, preSum)
    }
    return globalMax;
};