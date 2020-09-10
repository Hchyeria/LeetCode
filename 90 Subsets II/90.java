class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        if (nums == null || nums.length == 0) {
            return res;
        }
        dfs(nums, res, new ArrayList<>(), 0);
        return res;
    }
    
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> cur, int level) {
        if (level == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        cur.add(nums[level]);
        dfs(nums, res, cur, level + 1);
        
        while (level < nums.length - 1 && nums[level + 1] == nums[level]) {
            level++;
        }
        cur.remove(cur.size() - 1);
        dfs(nums, res, cur, level + 1);
    }

    // Time complexity: O(2^n).
	// Space complexity: O(n), because of call-stack.
}