import java.awt.List;

class Solution {
    // Solution 1: recursion
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> item = new ArrayList<>();
        DFS(nums, res, item, 0);
        return res;
    }
     
    private void DFS(int[] nums, List<List<Integer>> res, List<Integer> item, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<>(item));
            return;
        }
        item.add(nums[index]);
        DFS(nums, res, item, index + 1);
        item.remove(item.size() - 1);
        DFS(nums, res, item, index + 1);
    }

    // Time complexity is O(2^n).
    // Space complexity is O(n), because of call-stack.

    // Solution 2: for-loop
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        permutation(nums, res, new ArrayList<>(), 0);
        return res;
    }

    public void permutation(int[] nums, List<List<Integer>> res, List<Integer> item, int index) {
        res.add(new ArrayList<>(item));
        for (int i = index; i < nums.length; ++i) {
            item.add(nums[i]);
            permutation(nums, res, item, i + 1);
            item.remove(item.size() - 1);
        }
    }
    // Notice: If DFS uses a for loop (as in permutations), then
    // append-DFS-remove. If no for loop, then append-DFS-remove-DFS    
}