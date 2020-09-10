class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        if not nums or not len(nums):
            return len(nums)
        slow, fast = 0, 0
        while fast < len(nums):
            if nums[fast] == val:
                fast += 1
            else:
                nums[slow] = nums[fast]
                slow += 1
                fast += 1
        return slow