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
    // Recursion
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        helper(root);
        return root;
    }
    
    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.right);
        root.val += sum;
        sum = root.val;
        helper(root.left);
    }
    // Time = O(n)
    // Space = O(height)

    // Iterative
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        int sum = 0;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.offerFirst(cur);
                cur = cur.right;
            } else {
                TreeNode node = stack.pollFirst();
                node.val += sum;
                sum = node.val;
                cur = node.left;
            }
        }
        
        return root;
    }
    // Time = O(n)
    // Space = O(n)
}