class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        if not nums or len(nums) <= 1:
            return len(nums)
        slow, fast = 1, 1
        while fast < len(nums):
            if slow >= 1 and nums[fast] == nums[slow - 1]:
                fast += 1
            else:
                nums[slow] = nums[fast]
                slow += 1
                fast += 1
        return slow