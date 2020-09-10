/**
 * @param {number[]} nums
 * @param {number} val
 * @return {number}
 */
var removeElement = function(nums, val) {
    if (!nums || nums.length == 0) {
        return nums.length;
    }
    let slow = 0, fast = 0
    while (fast < nums.length) {
        if (nums[fast] === val) {
            fast++
        } else {
            nums[slow++] = nums[fast++];
        }
    }
    return slow;
}