var hasAlternatingBits = function(n) {
    while (n !== 0) {
        // if (n & 1 === ((n >>> 1) & 1))
        // not passed, must add parenthesis
        if ((n & 1) === ((n >>> 1) & 1)) {
            return false;
        }
        n >>>= 1
    }
    return true;
};

var hasAlternatingBits = function(n) {
    let num = n ^ (n >> 1)
    return !(num & (num + 1));
};
