/**
 * @param {number[]} nums
 */
var Solution = function(nums) {
    this.array = nums;
};

/**
 * Resets the array to its original configuration and return it.
 * @return {number[]}
 */
Solution.prototype.reset = function() {
    return this.array;
};

/**
 * Returns a random shuffling of the array.
 * @return {number[]}
 */
Solution.prototype.shuffle = function() {
    let temp = this.array.slice()
    for (let i = temp.length - 1; i >= 0; --i) {
        let index = Math.floor(Math.random() * (i + 1));
        [temp[i], temp[index]] = [temp[index], temp[i]]
    }
    return temp;
};

/** 
 * Your Solution object will be instantiated and called as such:
 * var obj = new Solution(nums)
 * var param_1 = obj.reset()
 * var param_2 = obj.shuffle()
 */