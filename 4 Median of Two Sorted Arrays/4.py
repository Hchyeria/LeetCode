class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        return statistics.median(nums1+nums2)


class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        m, n = len(nums1), len(nums2)
        if (m + n) % 2 == 0:
            one = kth(nums1, 0, nums2, 0, (m+n) // 2)
            two = kth(nums1, 0, nums2, 0, (m+n) // 2 + 1)
            return (one+two) / 2
        else:
            return kth(nums1, 0, nums2, 0, (m+n) // 2 + 1)


def kth(a, aLeft, b, bLeft, k):
    if aLeft >= len(a):
        return b[bLeft + k - 1]
    if bLeft >= len(b):
        return a[aLeft + k - 1]
    if k == 1:
        return min(a[aLeft], b[bLeft])
    aMid = aLeft + k // 2 - 1
    bMid = bLeft + k //2 - 1
    aTarget = float('inf') if aMid >= len(a) else a[aMid]
    bTarget = float('inf') if bMid >= len(b) else b[bMid]
    if aTarget <= bTarget:
        return kth(a, aMid + 1, b, bLeft, k - k // 2)
    else:
        return kth(a, aLeft, b, bMid+1, k - k // 2)