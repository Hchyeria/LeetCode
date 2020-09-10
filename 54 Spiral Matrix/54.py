class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        res = []
        if not matrix or not len(matrix) or not len(matrix[0]):
            return res
        m, n = len(matrix), len(matrix[0])
        offset = 0
        helper(res, matrix, m, n, offset)
        return res
    
def helper(res, matrix, m, n, offset):
    if m * n <= 0:
        return
    elif m * n == 1:
        res.append(matrix[offset][offset])
    elif m == 1:
        for i in range(n):
            res.append(matrix[offset][offset + i])
    elif n == 1:
        for i in range(m):
            res.append(matrix[offset + i][offset])
    else:
        for i in range(n - 1):
            res.append(matrix[offset][offset + i])
        for i in range(m - 1):
            res.append(matrix[offset + i][n - 1 + offset])
        for i in range(n - 1, 0, -1):
            res.append(matrix[offset + m - 1][offset + i])
        for i in range(m - 1, 0, -1):
            res.append(matrix[offset + i][offset])
        helper(res, matrix, m - 2, n - 2, offset + 1)