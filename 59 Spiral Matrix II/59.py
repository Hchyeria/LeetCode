class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        # every row will become same
        # if you write code like this
        # res = [[0] * n] * n
        res = [[0] * n for i in range(n)]
        if n <= 0:
            return []
        count = 1
        offset = 0
        helper(res, offset, n ,count)
        return res


def helper(res, offset, size, count):
    if size <= 0:
        return
    elif size == 1:
        res[offset][offset] = count
    else:
        for i in range(size - 1):
            res[offset][offset + i] = count
            count += 1
        for i in range(size - 1):
            res[offset + i][offset + size - 1] = count
            count += 1
        for i in range(size - 1, 0, -1):
            res[offset + size - 1][offset + i] = count
            count += 1
        for i in range(size - 1, 0, -1):
            res[offset + i][offset] = count
            count += 1
        helper(res, offset + 1, size - 2, count)
