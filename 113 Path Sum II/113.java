/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfs(res, new ArrayList<>(), root, sum);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> cur, TreeNode root, int sum) {
        cur.add(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null) {
            if (sum == 0) {
                res.add(new ArrayList<>(cur));
            }
            // remember remove the last element
            cur.remove(cur.size() - 1);
            return;
        }
        if (root.left != null) {
            dfs(res, cur, root.left, sum);
        }
        if (root.right != null) {
            dfs(res, cur, root.right, sum);
        }
        cur.remove(cur.size() - 1);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(height)
}