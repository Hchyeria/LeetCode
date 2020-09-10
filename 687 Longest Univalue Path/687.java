/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] maxVal = new int[1];
        dfs(root, maxVal);
        return maxVal[0];
    }
    
    private int dfs(TreeNode root, int[] maxVal) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        int left = dfs(root.left, maxVal);
        int right = dfs(root.right, maxVal);
        if (root.left != null && root.val == root.left.val) {
            left++;
        } else {
            left = 0;
        }
        if (root.right != null && root.val == root.right.val) {
            right++;
        } else {
            right = 0;
        }
        maxVal[0] = Math.max(maxVal[0], left + right);
        return Math.max(left, right);
    }
    // Time = O(N)
    // Space = O(height)
}