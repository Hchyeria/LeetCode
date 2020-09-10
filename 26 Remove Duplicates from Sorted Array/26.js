/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function(nums) {
    if (!nums || nums.length <= 1) {
        return nums.length
    }
    let slow = 1, fast = 1
    while (fast < nums.length) {
        if (slow >= 1 && nums[fast] === nums[slow - 1]) {
            fast++
        } else {
            nums[slow++] = nums[fast++]
        }
    }
    return slow;
};