class Solution:
    def longestOnes(self, A: List[int], K: int) -> int:
        res = 0
        zeros = 0
        left, right = 0, 0
        while right < len(A):
            if A[right] == 0:
                zeros += 1
            while zeros > K:
                zeros = zeros - 1 if A[left] == 0 else zeros
                left += 1
            right += 1
            res = max(res, right - left)
        return res