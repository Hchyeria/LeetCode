class Solution:
    def checkSubarraySum(self, nums: List[int], k: int) -> bool:
        if not nums or len(nums) < 2:
            return False
        sum = 0
        map = {}
        for i in range(len(nums)):
            sum += nums[i]
            sum = sum % k if k != 0 else sum
            if (sum == 0 and i >= 1) or (sum in map and i - map[sum] >= 2):
                return True
            elif sum not in map:
                map[sum] = i
                
        return False