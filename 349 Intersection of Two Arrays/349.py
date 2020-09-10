class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        return list(set(nums1) & set(nums2))
    
    def intersection2(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1.sort()
        nums2.sort()
        res = []
        l, r = 0, 0
        while l < len(nums1) and r < len(nums2):
            a, b = nums1[l], nums2[r]
            if a < b:
                l += 1
            elif a > b:
                r += 1
            else:
                res.append(a)
                l += 1
                r += 1
                # if there might be duplicated
                while l < len(nums1) and a == nums1[l]:
                    l += 1
                while r < len(nums2) and b == nums2[r]:
                    r += 1
        return res