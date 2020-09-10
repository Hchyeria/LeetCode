import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // Solution 1: 
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target == 0) {
            return res;
        }
        dfs(candidates, target, res, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> tempList, int index) {
        if (index == candidates.length) {
            if (target == 0) {
                res.add(add(tempList, candidates));
            }
            return;
        }
        int count = target / candidates[index];
        // <=
        for (int i = 0; i <= count; ++i) {
            tempList.add(i);
            dfs(candidates, target - i * candidates[index], res, tempList, index + 1);
            tempList.remove(tempList.size() - 1);         
        }
    }

    private List<Integer> add(List<Integer> list, int[] candidates) {
        int size = list.size();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            int num = list.get(i);
            for (int j = 0; j < num; ++j) {
                res.add(candidates[i]);
            }
        }
        return res;
    }

    // Time Complexity: O(target/min(coins)^candidates.length)
    // Space Complexity: O(n)
    
    // Solution 2: dfs recursion
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target == 0) {
            return res;
        }
        helper(candidates, target, res, new ArrayList<>(), 0);
        return res;
    }

    private void helper(int[] candidates, int target, List<List<Integer>> res, List<Integer> tempList, int index) {
        if (target < 0 || index == candidates.length) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        tempList.add(candidates[index]);
        helper(candidates, target - candidates[index], res, tempList, index); // no need index + 1, because maybe have same element
        tempList.remove(tempList.size() - 1);
        helper(candidates, target, res, tempList, index + 1);

    }
    // Time complexity is O(2^target)
    // Space complexity is O(target)

    //Solution 2: iterative way
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
		List<Integer> cur = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		DFS(candidates, target, cur, result, 0);
		return result;
	}

	private void DFS(int[] candidates, int target, List<Integer> cur, List<List<Integer>> result, int start) {
		if (target < 0) {
			return;
		} else if (target == 0) {
			result.add(new ArrayList<Integer>(cur));
			return;
		}
		for (int i = start; i < candidates.length; i++) {
			cur.add(candidates[i]);
			DFS(candidates, target - candidates[i], cur, result, i); // not i + 1 because we can reuse same elements
			cur.remove(cur.size() - 1);
		}
    }
    // Time complexity is O(n^target)
    // Space complexity is O(target)
    
    // Solution 3: DP
    public List<List<Integer>> combinationSum4(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target == 0) {
            return res;
        }
        // sort candidates to try them in asc order
        Arrays.sort(candidates);
        return dp(candidates, target);
    }

    private List<List<Integer>> dp(int[] candidates, int target) {
        List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 0; i <= target; ++i) {
            List<List<Integer>> res = new ArrayList<>();
            for (int j = 0; j < candidates.length && candidates[j] <= i; ++j) {
                if (candidates[j] == i) {
                    res.add(Arrays.asList(candidates[j]));
                } else {
                    List<List<Integer>> pre = dp.get(i - candidates[j]);
                    for (List<Integer> combo : pre) {
                        // avoid duplicated
                        // notice here is <= , where it can have same element
                        if (candidates[j] <= combo.get(0)) {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(candidates[j]);
                            temp.addAll(combo);
                            res.add(temp);
                        }
                    }
                }
            }
            dp.add(res);
        }
        return dp.get(target);

    }
    // Time complexity is O(target * n * combo)
    // Space complexity is O(target * combo)

}