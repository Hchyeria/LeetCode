var intersection = function(nums1, nums2) {
    nums1.sort((a, b) => a - b)
    nums2.sort((a, b) => a - b)
    let l = 0, r = 0;
    let res = []
    while (l < nums1.length && r < nums2.length) {
        let a = nums1[l], b = nums2[r]
        if (a < b) {
            l++
        } else if (a > b) {
            r++
        } else {
            res.push(a)
            while (l < nums1.length && nums1[l] === a) {
                l++
            }
            while (r < nums2.length && nums2[r] === b) {
                r++
            }
        }
    }
    return res;
};

// use Set
function intersection2(nums1, nums2) {
    const set = new Set(nums1);
    return [...new Set(nums2.filter(n => set.has(n)))];
}

// It's a bad solution, because the includes() will cause O(n) very expensive
var intersection3 = function(nums1, nums2) {
    return [...new Set(nums1.filter(e => nums2.includes(e)))]
};