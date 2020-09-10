import javax.swing.tree.TreeNode;

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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return dfs(root, sum);
    }
    
    private boolean dfs(TreeNode root, int sum) {
        if (root == null) {
            if (sum == 0) {
                return true;
            }
            return false;
        }
        boolean left = dfs(root.left, sum - root.val);
        boolean right = dfs(root.right, sum - root.val);
        if (root.left != null && root.right != null) {
            return left || right;
        }
        return root.left != null ? left : right;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(height)

    private boolean dfs2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && sum == root.val) {
            return true;
        }
        return dfs2(root.left, sum - root.val) || dfs2(root.right, sum - root.val);
    }
}