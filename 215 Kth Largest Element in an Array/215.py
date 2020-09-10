class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        if k == len(nums):
            return min(nums)
        else:
            return helper(nums, 0, len(nums) - 1, k - 1)

def helper(array, start, end, k):
    if start >= end:
        return array[k]
    p = partition(array, start, end)
    if p > k:
        return helper(array, start, p - 1, k)
    elif p < k:
        return helper(array, p + 1, end, k)
    else:
        return array[p]

def partition(array, start, end):
    p = start + random.randint(0, end - start)
    pivot = array[p]
    array[p], array[end] = array[end], array[p]
    left, right = start, end - 1
    while left <= right:
        if array[left] > pivot:
            left += 1
        elif array[right] <= pivot:
            right -= 1
        else:
            array[left], array[right] = array[right], array[left]
            left += 1
            right -= 1
    array[left] ,array[end] = array[end], array[left]
    return left