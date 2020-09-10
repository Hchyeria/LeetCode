/**
 * @param {number} n - a positive integer
 * @return {number} - a positive integer
 */
var reverseBits = function(n) {
    for (let left = 31, right = 0; left > right; --left, ++right) {
        let leftBit = (n >>> left) & 1
        let rightBit = (n >>> right) & 1
        if (leftBit !== rightBit) {
            n ^= (1 << left) | (1 << right)
        }
    }
    return n;
};