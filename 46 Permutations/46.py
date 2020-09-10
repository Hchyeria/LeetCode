class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        if not nums:
            return []
        if len(nums) == 1:
            return [nums]
        res = []
        dfs(res, nums, 0)
        return res
    
def dfs(res, nums, index):
    if index == len(nums):
        res.append(nums[:])
        return
    for i in range(index, len(nums)):
        nums[index], nums[i] = nums[i], nums[index]
        dfs(res, nums, index + 1)
        nums[index], nums[i] = nums[i], nums[index]