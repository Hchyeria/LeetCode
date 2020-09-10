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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        helper(res, root, 0);
        return res;
    }
    
    private void helper(List<Integer> res, TreeNode root, int index) {
        if (root == null) {
            return;
        }
        if (index == res.size()) {
            res.add(root.val);
        }
        helper(res, root.right, index + 1);
        helper(res, root.left, index + 1);
    }

    // Time = O(n)
    // Space = o(height)
}