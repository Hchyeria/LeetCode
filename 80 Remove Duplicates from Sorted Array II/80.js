/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function(nums) {
    if (!nums || nums.length <= 2) {
        return nums.length
    }
    let slow = 2, fast = 2
    while (fast < nums.length) {
        if (slow >= 2 && nums[fast] === nums[slow - 2]) {
            fast++
        } else {
            nums[slow++] = nums[fast++]
        }
    }
    return slow;
};