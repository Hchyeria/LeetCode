// LintCode
// 448. Inorder Successor in BST

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null) {
            return root;
        }
        TreeNode cur = root, pre = null;
        while (cur != null && cur != p) {
            if (p.val < cur.val) {
                pre = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (cur.right == null) {
            return pre;
        }
        cur = cur.right;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }
    // Time = O(height)
    // Space = O(1)
}