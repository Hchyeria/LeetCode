/**
 * @param {number} N
 * @return {number}
 */
var fib = function(N) {
    let a = 0, b = 1
    for (let i = 0; i < N; ++i) {
        let c = a + b
        a = b
        b = c
    }
    return a;
};