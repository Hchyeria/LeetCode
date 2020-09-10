/**
 * @param {number[][]} matrix
 * @return {number[]}
 */

var spiralOrder = function(matrix) {
    let res = []
    if (!matrix || !matrix.length || !matrix[0].length) {
        return res;
    }
    let m = matrix.length, n = matrix[0].length
    let start = 0, end = n - 1, up = 0, down = m - 1;
    while (start < end && up < down) {
        for (let i = start; i < end; ++i) {
            res.push(matrix[up][i])
        }
        for (let i = up; i < down; ++i) {
            res.push(matrix[i][end])
        }
        for (let i = end; i > start; --i) {
            res.push(matrix[down][i])
        }
        for (let i = down; i > up; --i) {
            res.push(matrix[i][start])
        }
        start++;
        end--;
        up++;
        down--;
    }
    if (start === end && up == down) {
        res.push(matrix[up][start])
    } else if (start === end) {
        for (let i = up; i <= down; ++i) {
            res.push(matrix[i][start])
        }
    } else if (up == down) {
        for (let i = start; i <= end; ++i) {
            res.push(matrix[up][i])
        }
    }
    return res;
};