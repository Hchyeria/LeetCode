/**
 * @param {number[]} nums
 * @return {number}
 */
var missingNumber = function(nums) {
    let hashSet = new Set(nums)
    for (let i = 0; i <= nums.length; ++i) {
        if (!hashSet.has(i)) {
            return i;
        }
    }
    return 0;
};

var missingNumber2 = function(nums) {
    let miss = nums.length
    for (let i = 0; i < nums.length; ++i) {
        miss ^= i ^ nums[i]
    }
    return miss;
};