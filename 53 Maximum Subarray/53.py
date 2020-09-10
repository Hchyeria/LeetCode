class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        if len(nums) == 1:
            return nums[0]
        preSum = 0
        globalMax = -float('inf')
        for i in range(len(nums)):
            preSum = max(preSum + nums[i], nums[i])
            globalMax = max(globalMax, preSum)
        return globalMax