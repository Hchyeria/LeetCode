class Solution:
    def solveNQueens(self, n: int) -> List[List[str]]:
        res = []
        if n <= 0:
            return res
        positions = nQueens(n)
        for solution in positions:
            solutionString = []
            for col in solution:
                item = ''
                for i in range(n):
                    if i == col:
                        item += 'Q'
                    else:
                        item += '.'
                solutionString.append(item)
            res.append(solutionString)
        return res

def nQueens(n: int):
    res = []
    usedCol = [False] * n
    usedDiagonals = [False] * (2 * n - 1)
    usedReverseDiagonals = [False] * (2 * n - 1)
    helper(res, [], n, 0, usedCol, usedDiagonals, usedReverseDiagonals)
    return res

def helper(res, cur, n, row, usedCol, usedDiagonals, usedReverseDiagonals):
    if row == n:
        res.append(cur[:])
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