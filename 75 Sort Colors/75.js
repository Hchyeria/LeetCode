/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var sortColors = function(nums) {
    if (!nums || nums.length == 1) {
        return;
    }
    let swap = (a, b) => ([nums[a], nums[b]] = [nums[b], nums[a]]);
    let i = 0, cur = 0, k = nums.length-1;

    while (cur <= k) {
        if (nums[cur] == 0) {
            swap(i++, cur++)
        } else if (nums[cur] == 1) {
            cur++
        } else {
            swap(cur, k--)
        }
    }
};
