class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        # 考虑边缘情况啊啊啊啊啊啊啊
        if not nums:
            return [-1, -1]
        start = find_start(nums, target)
        end = find_end(nums, target)
        return [start, end]



def find_start(nums: List[int], target: int):
    start = 0
    # 是 len - 1 !!
    end = len(nums) - 1
    while start < end - 1:
        mid = start + (end - start)//2
        if nums[mid] == target and nums[mid-1] < target:
            return mid
        elif nums[mid] >= target:
            end = mid
        else:
            start = mid
    if nums[start] == target:
        return start
    if nums[end] == target:
        return end
    return -1

def find_end(nums: List[int], target: int):
    start = 0
    end = len(nums) - 1
    while start < end - 1:
        mid = start + (end - start)//2
        if nums[mid] == target and nums[mid+1] > target:
            return mid
        elif nums[mid] <= target:
            start = mid
        else:
            end = mid
    if nums[end] == target:
        return end
    if nums[start] == target:
        return start
    return -1




class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if not nums:
            return [-1, -1]
        
        start, end = 0, len(nums) - 1
        while start <= end:             #注意判定條件即可
            mid = (start + end) // 2
            if target < nums[mid]:      #這裡要注意，既然mid不是解，就沒有必要保留
                end = mid - 1
            elif target > nums[mid]:    #同理，mid沒有必要保留
                start = mid + 1
            else:                       #找到以後左右再來各一次二項查找，注意這裡的前提是已經找到了
                #處理左邊，相等取其右
                start_l, end_l = start, mid
                while start_l < end_l - 1: #只有小於等於兩種情況
                    mid_l = (start_l + end_l) // 2
                    if nums[mid_l] < target:
                        start_l = mid_l + 1
                    else:
                        end_l = mid_l
                left_bound = start_l if nums[start_l] == target else end_l
                #處理右邊，相等取其左
                start_r, end_r = mid, end
                while start_r < end_r - 1: #只有小於等於兩種情況
                    mid_r = (start_r + end_r) // 2
                    if nums[mid_r] > target:
                        end_r = mid_r - 1
                    else:
                        start_r = mid_r
                right_bound = end_r if nums[end_r] == target else start_r
                return [left_bound, right_bound]
        return [-1, -1]


class Solution:
    def getStartEndIndex(self, nums, target, is_start):
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] < target:
                left = mid + 1
            if nums[mid] > target:
                right = mid - 1
            if nums[mid] == target:
                if is_start:
                    #这种方法也是对的
                    if mid == 0 or nums[mid - 1] != target:
                        return mid
                    else:
                        right = mid - 1
                else:
                    if mid == (len(nums) - 1) or nums[mid + 1] != target:
                        return mid
                    else:
                        left = mid + 1
        return -1
    
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        return [self.getStartEndIndex(nums, target, True), self.getStartEndIndex(nums, target, False)]
        
    
    