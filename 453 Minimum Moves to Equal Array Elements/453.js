/**
 * @param {number[]} nums
 * @return {number}
 */
var minMoves = function(nums) {
    return nums.reduce((acc, val) => acc + val, 0) - Math.min.apply(null, nums) * nums.length;
};