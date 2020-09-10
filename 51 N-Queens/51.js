/**
 * @param {number} n
 * @return {string[][]}
 */
var solveNQueens = function(n) {
    let res = []
    if (n <= 0) {
        return res;
    }
    let position = nQueens(n)
    for (let solution of position) {
        let solutionString = []
        for (let col of solution) {
            let item = ''
            for (let i = 0; i < n; ++i) {
                if (i === col) {
                    item += 'Q'
                } else {
                    item += '.'
                }
            }
            solutionString.push(item)
        }
        res.push(solutionString)
    }
    return res;
};

var nQueens = function(n) {
    let res = []
    let usedCol = new Array(n).fill(false)
    let usedDiagonals = new Array(2 * n - 1).fill(false)
    let usedReverseDiagonals = new Array(2 * n - 1).fill(false)
    helper(res, [], n, 0, usedCol, usedDiagonals, usedReverseDiagonals)
    return res;
}

var helper = function(res, cur, n, row, usedCol, usedDiagonals, usedReverseDiagonals) {
    if (row === n) {
        res.push(cur.slice())
        return;
    }
    for (let i = 0; i < n; ++i) {
        if (valid(row, i, n, usedCol, usedDiagonals, usedReverseDiagonals)) {
            cur.push(i)
            flip(row, i, n, usedCol, usedDiagonals, usedReverseDiagonals)
            helper(res, cur, n, row + 1, usedCol, usedDiagonals, usedReverseDiagonals)
            cur.pop()
            flip(row, i, n, usedCol, usedDiagonals, usedReverseDiagonals)
        }
    }
}

var valid = function(row, col, n, usedCol, usedDiagonals, usedReverseDiagonals) {
    return !usedCol[col] && !usedDiagonals[row + col] && !usedReverseDiagonals[n - 1 - row + col];
}

var flip = function(row, col, n, usedCol, usedDiagonals, usedReverseDiagonals) {
    usedCol[col] = !usedCol[col]
    usedDiagonals[row + col] = !usedDiagonals[row + col]
    usedReverseDiagonals[n - 1 - row + col] = !usedReverseDiagonals[n - 1 - row + col]
}
