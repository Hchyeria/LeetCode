class Solution:
    def combinationSum(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        res = []
        if target == 0:
            return res
        helper(candidates, target, res, [], 0)
        return res

def helper(candidates, target, res, item, index):
    if index == len(candidates):
        if target == 0:
            res.append(add(candidates, item))
        return
    count = target // candidates[index]
    for i in range(count + 1):
        item.append(i)
        helper(candidates, target - i * candidates[index], res, item, index + 1)
        item.pop()
    
def add(candidates, list):
    res = []
    for index, i in enumerate(list):
        res.extend([candidates[index]] * i)
    return res

    # DP
    def combinationSum2(self, candidates, target):
         """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        res = []
        if target == 0:
            return res
        # must sorted to make sure a ascending order
        candidates.sort()
        dp = []
        for i in range(target + 1):
            item = []
            for j in candidates:
                if j > i:
                    break
                if i == j:
                    item.append([j])
                else:
                    # sol maybe be not a list
                    # so let the j -> [j] item.append() -> item.extend()
                    # if j >= sol[-1] -> if sol and j >= sol[-1]
                    item.extend(
                        sol + [j]
                        for sol in dp[i - j]
                        if sol and j >= sol[-1]
                        )
            dp.append(item)
        return dp[-1]


