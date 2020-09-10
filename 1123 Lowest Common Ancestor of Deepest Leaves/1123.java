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
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        int[] globalMax = new int[] { Integer.MIN_VALUE };
        TreeNode[] res = new TreeNode[1];
        findLeaf(root, res, 0, globalMax);
        return res[0];
    }

    private int findLeaf(TreeNode root, TreeNode[] res, int deep, int[] globalMax) {
        // update should before (root == null) return 
        globalMax[0] = Math.max(globalMax[0], deep);

        if (root == null) {
            return deep;
        }
        
        int left = findLeaf(root.left, res, deep + 1, globalMax);
        int right = findLeaf(root.right, res, deep + 1, globalMax);
        if (left == globalMax[0] && right == globalMax[0]) {
            res[0] = root;
        }
        return Math.max(left, right);
    }

    // Time = O(n)
    // Space = O(n), worse case
     
 }