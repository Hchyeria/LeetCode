class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        globalMax = -float('inf')
        curMax, curMin = None, None
        preMax, preMin = 1, 1
        for i in nums:
            if i < 0:
                curMax = max(i, i * preMin)
                curMin = min(i, i * preMax)
            else:
                curMax = max(i, i * preMax)
                curMin = min(i, i * preMin)
            preMax = curMax
            preMin = curMin
            globalMax = max(globalMax, preMax)
        return globalMax