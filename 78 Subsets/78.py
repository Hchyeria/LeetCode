class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        res = []
        if not nums:
            return res
        item = []
        dfs(nums, res ,item, 0)
        return res
    
def dfs(nums, res ,item, index):
    if index == len(nums):
        res.append(item[:])
        return
    item.append(nums[index])
    dfs(nums, res, item, index + 1)
    item.pop()
    dfs(nums, res, item, index + 1)