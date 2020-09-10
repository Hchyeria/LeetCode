/**
 * @param {number[]} nums
 * @return {number}
 */


var compare_fn = function(a, b) {
    return a - b;
}


var binarySearch = function(ar, el, compare_fn) {
    var m = 0;
    var n = ar.length - 1;
    while (m < n - 1) {
        //var k = (n + m) >> 1; Nope, maybe cause overflow
        var k = m + (n - m) >> 1;
        var cmp = compare_fn(el, ar[k]);
        if (cmp > 0) {
            m = k;
        } else if(cmp < 0) {
            n = k;
        } else {
            return k;
        }
    }
    if (compare_fn(ar[m], el) >= 0) {
        return m
    }
    if (compare_fn(ar[n], el) >= 0) {
        return n
    }
    return n + 1
}

var lengthOfLIS = function(nums) {
    if (!nums.length){
        return 0;
    }
    let increaseSequence = [nums[0]]
    let len = 1
    nums.forEach(element => {
        let pos = binarySearch(increaseSequence, element, compare_fn)
        if (pos === len) {
            increaseSequence.push(element)
            len++
        } else {
            increaseSequence[pos] = element
        }
    });
    return len;
};

var binarySearch2 = function(ar, el, compare_fn) {
    let l = 0
    let r = ar.length - 1
    while (l <= r) {
        let m = (l + r) >> 1
        let cmp = compare_fn(el, ar[m])
        if (cmp > 0) {
            l = m + 1
        } else if (cmp < 0){
            r = m - 1
        } else {
            return m;
        }
    }
    return -l - 1
}
