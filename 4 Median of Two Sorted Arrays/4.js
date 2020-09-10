/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var findMedianSortedArrays = function(nums1, nums2) {
    let m = nums1.length, n = nums2.length;
    if ((m + n) % 2 === 0){
        let one = kth(nums1, 0, nums2, 0, (m+n) >> 1)
        let two = kth(nums1, 0, nums2, 0, ((m+n) >> 1) + 1)
        return (one + two) / 2;
    } else {
        return kth(nums1, 0, nums2, 0, ((m+n) >> 1) + 1)
    }
};

/**
 * @param {number[]} a
 * @param {number} aLeft
 * @param {number[]} b
 * @param {number} bLeft
 * @param {number} k
 * @return {number}
 */
var kth = function(a , aLeft, b, bLeft, k) {
    if (aLeft >= a.length) {
        return b[bLeft + k - 1];
    }
    if (bLeft >= b.length) {
        return a[aLeft + k -1];
    }
    if (k === 1) {
        return Math.min(a[aLeft], b[bLeft]);
    }

    let aMid = aLeft + (k >> 1) - 1
    let bMid = bLeft + (k >> 1) - 1
    let aTarget = aMid >= a.length ? Infinity : a[aMid]
    let bTarget = bMid >= b.length ? Infinity : b[bMid];
    if (aTarget <= bTarget) {
        return kth(a, aMid + 1, b, bLeft, k - (k >> 1));
    } else {
        return kth(a, aLeft, b, bMid + 1, k - (k >> 1));
    }
}