class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        if not matrix or not len(matrix) or not len(matrix[0]):
            return False
        row = len(matrix)
        col = len(matrix[0])
        left = 0
        right = row*col - 1

        while left <= right:
            mid = left + (right - left) // 2
            m = mid // col
            n = mid % col
            item = matrix[m][n]

            if item == target:
                return True
            elif item < target:
                left = mid + 1
            else:
                right = mid - 1
            
        return False

        