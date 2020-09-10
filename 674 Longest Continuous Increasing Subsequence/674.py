class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        if nums == None:
            return -1
        if len(nums) == 0:
            return 0
        cur = 1
        globalMax = 1
        for i in range(1, len(nums)):
            if nums[i] > nums[i - 1]:
                cur += 1
                globalMax = max(globalMax, cur)
            else:
                cur = 1
        return globalMax