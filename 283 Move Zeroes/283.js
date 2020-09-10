/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var moveZeroes = function(nums) {
    if (!nums || nums.length == 1) {
        return;
    }

    let slow = 0, fast = 0;
    while (fast < nums.length) {
        if (nums[fast] !== 0) {
            if (slow !== fast) {
                [nums[slow], nums[fast]] = [nums[fast], nums[slow]]
            }
            slow++
        }
        fast++
    }
};