/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var permute = function(nums) {
    if (!nums || !nums.length) {
        return [];
    }
    if (nums.length === 1) {
        return [nums];
    }
    
    let res = []
    dfs(nums, res ,0)
    return res;
};

var dfs = function(nums, res ,index) {
    if (index == nums.length) {
        res.push(nums.slice())
        return;
    }
    for (let i = index; i < nums.length; ++i) {
        swap(nums, i, index)
        dfs(nums, res, index + 1)
        swap(nums, i, index)
    }
}

var swap = function(nums, i, j) {
    let temp = nums[i]
    nums[i] = nums[j]
    nums[j] = temp
}