/**
 * @param {string} s1
 * @param {string} s2
 * @return {boolean}
 */
var checkInclusion = function(s1, s2) {
    if (!s1 || s1.length == 0) {
        return true;
    }
    let count = new Array(26).fill(0)
    for (let i = 0; i < s1.length; ++i) {
        count[s1[i].charCodeAt() - 'a'.charCodeAt()]++
    }
    let left = 0, right = 0
    let a = 'a'.charCodeAt()
    while (right < s2.length) {
        let index = s2[right].charCodeAt() - a
        if (count[index] > 0) {
            count[index]--
            right++
        } else {
            count[s2[left].charCodeAt() - a]++
            left++
        }
        
        if (right - left == s1.length) {
            return true;
        }
    }
    return false;
};