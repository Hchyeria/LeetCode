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

    // Solution 1: recursion
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        // remember to set null
        root.left = null;
        TreeNode right = root.right;
        flatten(left);
        flatten(right);
        if (left != null) {
            root.right = left;
            TreeNode cur = left;
            while (cur.right != null) {
                cur = cur.right;
            }
            cur.right = right;
        }
    }

    // Time = O(n)
    // Space = O(height)

    // Solution 2: iterative
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollFirst();
            if (pre != null) {
                pre.right = node;
            }
            if (node.right != null) {
                stack.offerFirst(node.right);
            }
            if (node.left != null) {
                stack.offerFirst(node.left);
            }
            node.left = null;
            pre = node;
        }
    }

    // Time = O(n)
    // Space = O(n)
}