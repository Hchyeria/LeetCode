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
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = getHeight(root.left);
        if (left == -1) {
            return -1;
        }
        
        int right = getHeight(root.right);
        if (right == -1) {
            return -1;
        }
        
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
// Time complexity is O(n).
// Space complexity is O(n), when the binary tree is highly unbalanced.