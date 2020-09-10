class Solution:
    def findPos(self, array, low, high, value):
        mid = 0
        if not array:
            return 0
        while low <= high:
            mid = (high + low) // 2
            if(array[mid] == value):
                return mid
            elif array[mid] < value:
                low = mid + 1
            else:
                high = mid - 1
        if low < len(array) and array[low] > value:
            return low
        elif high >= 0 and array[high] > value:
            return high
        return mid

    def lengthOfLIS(self, nums: List[int]) -> int:
        if not nums:
            return 0
        increseSequence = [nums[0]]
        len = 1
        for num in nums:
            if num > increseSequence[-1]:
                increseSequence.append(num)
                len += 1
            else:
                pos = self.findPos(increseSequence, 0, len - 1, num)
                increseSequence[pos] = num
        return len


class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        if not nums:
            return 0
        stack = [nums[0]]
        for n in nums[1:]:
            if n > stack[-1]:
                stack.append(n)
            else:
                # binary search
                index = bisect.bisect_left(stack, n) 
                stack[index] = n
        return len(stack)