/**
 * @param {number[][]} matrix
 * @param {number} k
 * @return {number}
 */
var kthSmallest = function(matrix, k) {
    let m = matrix.length, n = matrix[0].length
    let low = matrix[0][0], high = matrix[m - 1][n - 1]
    while (low < high) {
        let mid = low + ((high - low) >> 1)
        let count = 0
        for (let i = 0; i < m; ++i) {
            count += countSmallerAndEqual(matrix[i], mid)
        }
        if (count < k) {
            low = mid + 1
        } else {
            high = mid
        }
    }

    return low
};

var countSmallerAndEqual = function(array, target) {
    if (!array || !array.length) {
        return 0;
    }
    let length = array.length;
    if (target >= array[length - 1]) {
        return length;
    }
    if (target < array[0]) {
        return 0;
    }
    let left = 0, right = length
    while (left < right) {
        let mid = left + ((right - left) >> 1)
        // Notice here is <=
        if (array[mid] <= target) {
            left = mid + 1
        } else {
            right = mid
        }
    } 
    return left;
}