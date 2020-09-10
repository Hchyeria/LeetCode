class Solution:
    def findClosestElements(self, arr, k: int, x: int):
        if not arr or not len(arr):
            return arr
        if x <= arr[0]:
            return arr[:k]
        elif x >= arr[-1]:
            return arr[-k:]
        else:
            index = self.largeSmaller(arr, x)
            rightPointer = index + 1
            res = []
            while len(res) < k:
                if rightPointer >= len(arr) or (index >=0 and (x - arr[index] <= arr[rightPointer] - x)):
                    res.insert(0, arr[index])
                    index -= 1
                else:
                    res.append(arr[rightPointer])
                    rightPointer += 1
            return res
    
    def largeSmaller(self, arr, x: int) -> int:
        left = 0
        right = len(arr) - 1

        while left < right - 1:
            mid = left + (right - left) // 2
            if arr[mid] == x:
                return mid
            elif arr[mid] < x:
                left = mid
            else:
                right = mid
        if arr[right] < x:
            return right
        else:
            return left
#
# class Solution:
#     def findClosestElements(self, arr: List[int], k: int, x: int) -> List[int]:
#
#         A = [(a-x, 1) if a > x else (x-a, 0) for a in arr]
#
#         res = [x - a[0] if a[1] == 0 else a[0] + x for a in sorted(A)[:k]]
#
#         return sorted(res)

