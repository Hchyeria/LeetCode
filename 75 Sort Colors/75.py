class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        if not nums or len(nums) <= 1:
            return
        
        def swap(i , j):
            nums[i], nums[j] = nums[j], nums[i]

        i = 0
        cur = 0
        k = len(nums) -1 
        while cur <= k:
            if nums[cur] == 0:
                swap(i , cur)
                i += 1
                cur += 1
            elif nums[cur] == 1:
                cur += 1
            else:
                swap(k , cur)
                k -= 1