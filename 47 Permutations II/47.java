class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        if (nums.length <= 1) {
            result.add(toList(nums));
            return result;
        }
        dfs(nums, result, 0);
        return result;
        
    }

    private void dfs(int[] array, List<List<Integer>> res, int index) {
        if (index == array.length) {
            res.add(toList(array));
            return;
        }
        HashSet<Integer> ints = new HashSet<>();
        for (int i = index; i < array.length; ++i) {
            // will return false if array[i] has already exits in set, so will be pruned
            if (ints.add(array[i])) {
                swap(array, index, i);
                dfs(array, res, index + 1);
                swap(array, index, i);
            }
        }
    }

    private void swap(int[] array, int j, int i) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private List<Integer> toList(int[] array) {
        List<Integer> res = new ArrayList<>();
        for (int i : array) {
            res.add(i);
        }
        return res;
    }

    // Time complexity: O(n + n*(n-1) + n*(n-1)*(n-2) + ... + n!) = O(n!), ignore the toList
    // Space complexity: O(n)
}