class Solution:
    def findReplaceString(self, S: str, indexes: List[int], sources: List[str], targets: List[str]) -> str:
        s = list(S)
        # must reverse=True, from end to start
        # because len(x) may not equal len(y)
        # after replace, the front chars not be affected
        for i, x, y in sorted(zip(indexes, sources, targets), reverse=True):
            if all(i + k < len(s) and s[i + k] == x[k] for k in range(len(x))):
                # len(x) may not equal len(y)
                s[i: i + len(x)] = list(y)
        return ''.join(s)
