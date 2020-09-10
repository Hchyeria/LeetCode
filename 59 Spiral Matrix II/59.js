/**
 * @param {number} n
 * @return {number[][]}
 */
var generateMatrix = function(n) {
    // map, forEach do not work in new Array()
    // https://stackoverflow.com/questions/25512771/what-is-array-apply-actually-doing
    /*  
        function Foo() { 
            return this; 
        }
        
        var a = Foo();       //returns window object
        var b = new Foo();   //returns empty object of foo
        
        a instanceof Window;  // true
        a instanceof Foo;     // false
        
        b instanceof Window;  // false
        b instanceof Foo;     // true 
    */
   
    let res = Array.apply(null, new Array(n)).map(ele => new Array(n))

    if (n <= 0) {
        return res;
    }
    let count = 1
    let offset = 0
    helper(res, offset, n, count)
    return res
};

var helper = function(res, offset, size, count) {
    if (size <= 0) {
        return;
    } else if (size === 1) {
        res[offset][offset] = count
    } else {
        for (let i = 0; i < size - 1; ++i) {
            res[offset][offset + i] = count++
        }
        for (let i = 0; i < size - 1; ++i) {
            res[offset + i][offset + size - 1] = count++
        }
        for (let i = size - 1; i > 0; --i) {
            res[offset + size - 1][offset + i] = count++
        }
        for (let i = size - 1; i > 0; --i) {
            res[offset + i][offset] = count++
        }
        helper(res, offset + 1, size - 2, count)
    }
}