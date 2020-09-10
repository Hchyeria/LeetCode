class Solution:
    def canJump(self, nums: List[int]) -> bool:
        if len(nums) == 1:
            return True
        curMax = 0
        for i in range(len(nums)):
            curMax = max(curMax, i + nums[i]) if i <= curMax else curMax
        return curMax >= len(nums) - 1