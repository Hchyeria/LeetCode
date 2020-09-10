/**
 * @param {number} x
 * @param {number} n
 * @return {number}
 */
var myPow = function(x, n) {
    if (n === 1) {
        return x;
    } else if (n === 0) {
        return 1;
    } else {
        if (n < 0) {
            return myPow(1 / x, -n);
        }
        let res = myPow(x, Math.floor(n / 2));
        if (n % 2 === 0) {
            return res * res;
        } else {
            return res * res * x;
        }
    }
};