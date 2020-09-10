/**
 * initialize your data structure here.
 */
var MinStack = function() {
    this.buffer = []
    this.miniMum = []
};

/** 
 * @param {number} x
 * @return {void}
 */
MinStack.prototype.push = function(x) {
    this.buffer.push(x)
    if (!this.miniMum.length || this.miniMum[this.miniMum.length-1] > x) {
        this.miniMum.push(x);
    } else {
        this.miniMum.push(this.miniMum[this.miniMum.length-1])
    }
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function() {
    this.buffer.pop();
    this.miniMum.pop();
};

/**
 * @return {number}
 */
MinStack.prototype.top = function() {
    return this.buffer[this.buffer.length-1]
};

/**
 * @return {number}
 */
MinStack.prototype.getMin = function() {
    return this.miniMum[this.miniMum.length-1]
};

/** 
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(x)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */