class Solution:
    def uniqueLetterString(self, S: str) -> int:
        cash = collections.defaultdict(list)
        res = 0
        for i, s in enumerate(S):
            if not cash[s]:
                cash[s] = -1, -1
            res += (i - cash[s][1]) * (cash[s][1] - cash[s][0])
            # cash[s] = cash[s][1], i 
            # faster than cash[s] = [cash[s][1], i]
            cash[s] = cash[s][1], i
        n = len(S)
        for key, val in cash.items():
            res += (n - val[1]) * (val[1] - val[0])
        return res % (10**9 + 7)

    # ord(c) - ord('A')
