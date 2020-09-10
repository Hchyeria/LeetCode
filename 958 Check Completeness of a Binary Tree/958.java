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
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean flag = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(node.left);
            }
            
            if (node.right == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(node.right);
            }
        }
        return true;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    // since the last layer of a binary tree contains half the total number of nodes
 }