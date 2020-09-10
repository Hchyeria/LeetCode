/**
 * @param {string} s
 * @param {string} t
 * @return {boolean}
 */
var isAnagram = function(s, t) {
    let sLength = s.length, tLength = t.length
    if (sLength !== tLength) return false;
    let len = 26
    let temp  = Array.apply(null, Array(len)).map(() => 0)
    for (let i = 0; i < sLength; ++i) {
        temp[s.charCodeAt(i) - 97]++
        temp[t.charCodeAt(i) - 97]--
    }
    for (let j = 0; j < len; ++j) {
        if (temp[j] !== 0) return false;

    }
    return true;
};

