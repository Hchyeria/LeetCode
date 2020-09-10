class Solution:
    def missingNumber(self, nums: List[int]) -> int:
        # lowcase set not Set !
        hash_set = set(nums)
        for i in range(len(nums) + 1):
            if i not in hash_set:
                return i
        return -1
    
    def missingNumber2(self, nums: List[int]) -> int:
        miss = len(nums)
        for i, num in enumerate(nums):
            miss ^= i ^ num
        return miss