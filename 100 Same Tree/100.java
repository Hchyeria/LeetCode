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
    // Time complexity is O(n).
    // Space complexity is O(n). worse case 
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    // explicitly use a stack
    // will be outdated
    // Time complexity is O(n).
    // Space complexity is O(n).
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(p);
        stack.offerFirst(q);
        while (!stack.isEmpty()) {
            TreeNode right = stack.pollFirst();
            TreeNode left = stack.pollFirst();
            if (left == null || right == null) {
                if (left != right) {
                    return false;
                }
            } else if (left.val != right.val) {
                return false;
            } else {
                stack.offerFirst(left.left);
                stack.offerFirst(right.left);
                stack.offerFirst(left.right);
                stack.offerFirst(right.right);
            }
        }

        return true;
    }
}