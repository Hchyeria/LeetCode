/**
 * @param {string} S
 * @param {number[]} indexes
 * @param {string[]} sources
 * @param {string[]} targets
 * @return {string}
 */
var findReplaceString = function(S, indexes, sources, targets) {
    let match = S.split('')

    indexes.forEach((ele, i) => {
        let s = sources[i]
        let t = targets[i]

        if (compareSame(match, s, ele)) {
            match[ele] = t
            let num = s.length - 1
            while (num) {
                match[ele + num] = ''
                num -= 1
            }
        }
    })
    return match.join('')
};

var compareSame = function(stringArray, s, index) {
    for (let i = 0; i < s.length; ++i) {
        if (stringArray[index + i] !== s[i]) {
            return false;
        }
    }
    return true;
}