/**
 * @param {number[]} A
 * @param {number} K
 * @return {number}
 */
var longestOnes = function(A, K) {
    let res = 0
    let zeros = 0
    for (let left = 0, right = 0; right < A.length; ++right) {
        if (A[right] === 0) {
            zeros++
        }
        while (zeros > K) {
            zeros = A[left++] == 0 ? zeros - 1 : zeros
        }
        // there is right - left + 1
        // not right - left
        // because right++ after this code
        res = Math.max(res, right - left + 1)
    }
    return res;
};