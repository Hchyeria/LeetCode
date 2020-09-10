
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        if (nums.length == 1) {
            res.add(toList(nums));
            return res;
        }
        dfs(nums, res ,0);
        return res;
    }

    private void dfs(int[] array, List<List<Integer>> res, int index) {
        if (index == array.length) {
            // if we use new ArrayList<>(Arrays.asList(array))
            // will throw incompatible types error
            res.add(toList(array));
            return;
        }
        for (int i = index; i < array.length; ++i) {
            swap(array, index, i);
            dfs(array, res, index + 1);
            swap(array, index, i);
        }
    }

    private void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    
    // write a loop help function will be much faster than 
    // Arrays.stream(array).boxed().collect(Collectors.toList())
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