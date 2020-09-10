class Solution:
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        if not matrix or not len(matrix):
            return False
        if not matrix[0] or not len(matrix[0]):
            return False
        m = len(matrix)
        n = len(matrix[0])
        i = 0
        j = n - 1

        while i <= m - 1 and j >= 0:
            if matrix[i][j] == target:
                return True
            elif matrix[i][j] < target:
                i += 1
            else:
                j -= 1
        return False