/**
 * @param {number} num
 * @return {string}
 */
var toHex = function(num) {
    if (num === 0) {
        return '0';
    }
    let map = "abcdef"
    let res = []
    while (num !== 0) {
        let hexNum = num  & 0xF
        res.push(hexNum < 10 ? hexNum : map[hexNum - 10])
        num >>>= 4
    }

    return res.reverse().join('')
};