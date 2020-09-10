/**
 * @param {number[]} arr
 * @param {number} k
 * @param {number} x
 * @return {number[]}
 */
var findClosestElements = function(arr, k, x) {
    if (!arr || !arr.length) {
        return arr;
    }
    let res = []
    if (x <= arr[0]) {
        return arr.slice(0, k)
    } else if (x >= arr[arr.length-1]) {
        return arr.slice(arr.length-k, arr.length)
    } else {
        let index = largeSmaller(arr, x)
        let rightPointer = index + 1
        while (res.length < k) {
            if (rightPointer >= arr.length 
                || (index >= 0 && x - arr[index] <= arr[rightPointer] - x)) {
                res.unshift(arr[index--])
            } else {
                res.push(arr[rightPointer++])
            }
        }
    }
    return res;

};

var largeSmaller = function(arr, x) {
    let left = 0
    let right = arr.length - 1

    while (left < right - 1) {
        let mid = left + ((right - left) >> 1)
        if (arr[mid] == x) {
            return mid;
        } else if (arr[mid] < x) {
            left = mid
        } else {
            right = mid - 1
        }
    }

    if (arr[right] < x) {
        return right
    } else {
        return left
    }
}
