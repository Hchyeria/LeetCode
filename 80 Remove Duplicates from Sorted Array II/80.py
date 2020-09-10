class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        if not nums or len(nums) <= 2:
            return len(nums)
        slow, fast = 2, 2
        while fast < len(nums):
            if slow >= 2 and nums[fast] == nums[slow - 2]:
                fast += 1
            else:
                nums[slow] = nums[fast]
                slow += 1
                fast += 1
        return slow