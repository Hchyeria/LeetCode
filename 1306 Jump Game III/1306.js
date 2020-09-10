/**
 * @param {number[]} arr
 * @param {number} start
 * @return {boolean}
 */
var canReach = function(arr, start) {
    if (arr.length === 1) {
        return true;
    }
    return dfs(arr, start);
};

var dfs = function(arr, index) {
    if (index < 0 || index >= arr.length || arr[index] >= arr.length) {
        return false;
    }
    if (arr[index] === 0) {
        return true;
    }
    let jump = arr[index]
    arr[index] += arr.length
    return dfs(arr, index - jump) || dfs(arr, index + jump);
} 