/**
 * @param {number[]} nums
 * @return {number}
 */
var jump = function(nums) {
    let n = nums.length
    if (n == 1) {
        return 0;
    }
    let jump = 0
    let i = 0, curEnd = 0
    let farthestEnd = 0
    for ( ; i < n; ++i) {
        if (i > curEnd) {
            jump++
            if (curEnd === farthestEnd) {
                return -1;
            }
            curEnd = farthestEnd
            if (curEnd >= n - 1) {
                break;
            }
        }
        farthestEnd = Math.max(farthestEnd, i + nums[i])
    }
    return jump;
};