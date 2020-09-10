class Solution:
    def jump(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 1:
            return 0
        jump = 0
        curEnd = 0
        farthestEnd = 0
        for i in range(n):
            if i > curEnd:
                jump += 1
                if curEnd == farthestEnd:
                    return -1
                curEnd = farthestEnd
            farthestEnd = max(farthestEnd, i + nums[i])
        return jump