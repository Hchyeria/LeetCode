/**
 * @param {number[]} nums
 * @return {number}
 */
var maxProduct = function(nums) {
    let globalMax = -Infinity
    let curMax, curMin;
    let preMin = 1, preMax = 1
    for (let num of nums) {
        if (num < 0) {
            curMax = Math.max(num, preMin * num)
            curMin = Math.min(num, preMax * num)
        } else {
            curMax = Math.max(num, preMax * num)
            curMin = Math.min(num, preMin * num)
        }
        preMax = curMax
        preMin = curMin
        globalMax = Math.max(globalMax, preMax)
    }
    return globalMax
    
};