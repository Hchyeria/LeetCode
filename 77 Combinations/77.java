class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 1 || k == 0) {
            return res;
        }
        dfs(res, new ArrayList<>(), 1, n, k);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> cur, int curVal, int n, int k) {
        if (curVal > n) {
            return;
        }
        cur.add(curVal);
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            cur.remove(cur.size() - 1);
            dfs(res, cur, curVal + 1, n, k);
        } else {
            dfs(res, cur, curVal + 1, n, k);
            cur.remove(cur.size() - 1);
            dfs(res, cur, curVal + 1, n, k);
        }
        
    }
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)

    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 1 || k == 0) {
            return res;
        }
        dfs(res, new ArrayList<>(), 1, n, k);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> cur, int index, int n, int k) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = index; i <= n; ++i) {
            cur.add(i);
            dfs(res, cur, i + 1, n, k);
            cur.remove(cur.size() - 1);
        }
    }
    // Time Complexity: O(n^k)
    // Space Complexity: O(k)
}