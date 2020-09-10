class Solution:
    def totalNQueens(self, n: int) -> int:
        return nQueens(n)[0]


def nQueens(n: int):
    res = [0]
    usedCol = [False] * n
    usedDiagonals = [False] * (2 * n - 1)
    usedReverseDiagonals = [False] * (2 * n - 1)
    helper(res, [], n, 0, usedCol, usedDiagonals, usedReverseDiagonals)
    return res

def helper(res, cur, n, row, usedCol, usedDiagonals, usedReverseDiagonals):
    if row == n:
        res[0] += 1
        return
    for i in range(n):
        if valid(cur, row, i, n, usedCol, usedDiagonals, usedReverseDiagonals):
            cur.append(i)
            flip(row, i, n, usedCol, usedDiagonals, usedReverseDiagonals)
            helper(res, cur, n, row + 1, usedCol, usedDiagonals, usedReverseDiagonals)
            cur.pop()
            flip(row, i, n, usedCol, usedDiagonals, usedReverseDiagonals)

def valid(cur, row, col, n, usedCol, usedDiagonals, usedReverseDiagonals):
    return not usedCol[col] and not usedDiagonals[row + col] and not usedReverseDiagonals[n - 1 - row + col]

def flip(row, col, n, usedCol, usedDiagonals, usedReverseDiagonals):
    usedCol[col] = not usedCol[col]
    usedDiagonals[row + col] = not usedDiagonals[row + col]
    usedReverseDiagonals[n - 1 - row + col] = not usedReverseDiagonals[n - 1 - row + col]