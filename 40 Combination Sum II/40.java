class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        dfs(candidates, res, new ArrayList<>(), target, 0);
        return res;
    }
    
    private void dfs(int[] candidates, List<List<Integer>> res, 
                     List<Integer> cur, int sum, int level) {
        if (sum <= 0 || level == candidates.length) {
            if (sum == 0) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }
        
        cur.add(candidates[level]);
        dfs(candidates, res, cur, sum - candidates[level], level + 1);
        
        while (level < candidates.length - 1 && candidates[level] == candidates[level + 1]) {
            level++;
        }
        cur.remove(cur.size() - 1);
        dfs(candidates, res, cur, sum, level + 1);
        
    }
    // Time = O(2^n)
    // Space = O(n)
}