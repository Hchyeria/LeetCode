/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function(matrix, target) {
    if (!matrix || !matrix.length) {
        return false;
    }

    if (!matrix[0] || !matrix[0].length) {
        return false;
    }

    let m = matrix.length, n = matrix[0].length;
    let i = 0, j = n - 1;
    while (i <= m -1 && j >= 0) {
        if (matrix[i][j] === target) {
            return true;
        } else if (matrix[i][j] < target) {
            i++;
        } else {
            j--;
        }
    }
    return false;
};

searchMatrix([[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], 5)