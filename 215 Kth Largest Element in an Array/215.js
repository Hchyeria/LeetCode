/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var findKthLargest = function(nums, k) {
    if (k === nums.length) {
        return Math.min.apply(null, nums);
    }
    return helper(nums, 0, nums.length - 1, k - 1);
};

/**
 * @param {number[]} array
 * @param {number} start
 * @param {number} end
 * @param {number} k
 * @return {number}
 */
var helper = function(array, start, end, k) {
    if (start >= end) {
        return array[k];
    }
    let p = partition(array, start, end)
    if (p > k) {
        return helper(array, start, p - 1, k);
    } else if (p < k) {
        return helper(array, p + 1, end, k)
    } else {
        return array[p];
    }
}

/**
 * @param {number[]} array
 * @param {number} start
 * @param {number} end
 * @return {number}
 */
var partition = function(array, start, end) {
    let p = start + Math.floor(Math.random() * (end - start + 1))
    let pivot = array[p];
    swap(array, end, p)
    let left = start, right = end - 1;
    while (left <= right) {
        if (array[left] > pivot) {
            left++;
        } else if (array[right] <= pivot) {
            right--;
        } else {
            swap(array, left++, right--)
        }
    }
    swap(array, left, end)
    return left;
}

var swap = function(array, i, j) {
    return [array[i], array[j]] = [array[j], array[i]]

}