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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return checkSymmetric(root.left, root.right);
    }

    private boolean checkSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        } else {
            return checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left);
        }
    }

    // Time complexity is O(n).
    // Space complexity is O(n).
    // Note that the worst case is when the binary tree is unbalanced.

    // explicitly use the stack
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root.left);
        stack.offerFirst(root.right);
        while (!stack.isEmpty()) {
            TreeNode right = stack.pollFirst();
            TreeNode left = stack.pollFirst();
            if (left == null || right == null) {
                if (left != right) {
                  return false;  
                }
            }  else if (left.val != right.val) {
                return false;
            } else {
                // need to pay attention to right != null && left != null
                // of course we don't need to check here
                stack.offerFirst(right.right);
                stack.offerFirst(left.left);
                stack.offerFirst(right.left);
                stack.offerFirst(left.right);
            }
        }
        return true;
    }
    

}