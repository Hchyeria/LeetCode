class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(n,0, 0, res, new StringBuffer());
        return res;
    }

    private void dfs(int n, int l, int r, List<String> res, StringBuffer tempString) {
        if (l == n && r == n) {
            res.add(tempString.toString());
            return;
        }
        if (l < n) {
            tempString.append('(');
            dfs(n, l + 1, r, res, tempString);
            tempString.deleteCharAt(tempString.length() - 1);
        }

        if (l > r) {
            tempString.append(')');
            dfs(n, l, r + 1, res, tempString);
            tempString.deleteCharAt(tempString.length() - 1);
        }
    }

    // Time Complexity: O(2^(2n))
    // However, it is the upper bound since the tree is not tight, some branches are cut off
    // Space complexity is O(n)
}