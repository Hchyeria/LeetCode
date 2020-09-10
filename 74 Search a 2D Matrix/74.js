/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function(matrix, target) {
    // [[]] consider this case
    // matrix.length == 0 matrix[0].length != 0
    if (!matrix || !matrix.length || !matrix[0].length) {
        return false;
    }

    let row = matrix.length
    let col = matrix[0].length

    let left = 0, right = row*col - 1;
    while (left <= right) {
        let mid = left + ((right - left) >> 1)
        let m = Math.floor(mid / col)
        let n = mid % col
        let item = matrix[m][n]
        if (item == target) {
            return true;
        } else if (item < target) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }

    return false;

};