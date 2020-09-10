/**
 * @param {string} S
 * @return {number}
 */
var uniqueLetterString = function(S) {
    if (!S) {
        return 0;
    }
    if (S.length <= 1) {
        return S.length;
    }
    let charMap = new Map()
    let res = 0
    let n = S.length
    for (let i = 0; i < n; ++i) {
        if (!charMap.get(S[i])) {
            charMap.set(S[i], [-1, -1])
        }
        let index = charMap.get(S[i])
        res += (index[1] - index[0]) * (i - index[1])
        charMap.set(S[i], [index[1], i])
    }
    
    for (let val of charMap.values()) {
        res += (n - val[1]) * (val[1] - val[0])
    }
    return res % (Math.pow(10, 9) + 7);
};