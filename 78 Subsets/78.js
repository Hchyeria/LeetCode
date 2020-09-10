/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var subsets = function(nums) {
    let res = []
    if (!nums || !nums.length) {
        return res;
    }
    let item = []
    dfs(nums, res, item, 0)
    return res;
};

var dfs = function(nums, res, item, index) {
    if (index === nums.length) {
        res.push(item.slice())
        return;
    }
    item.push(nums[index])
    dfs(nums, res ,item, index + 1)
    item.pop()
    dfs(nums, res, item, index + 1)
}