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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0
            || postorder == null || postorder.length == 0
            || inorder.length != postorder.length) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        int n = inorder.length;
        for (int i = 0; i < n; ++i) {
            indexMap.put(inorder[i], i);
        }
        
        return helper(inorder, 0, n - 1, postorder, n - 1, indexMap);
    }
    
    private TreeNode helper(int[] inorder, int inLeft, int inRight, 
                            int[] postorder, int postRight, Map<Integer, Integer> indexMap) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postRight]);
        int rightSize = inRight - indexMap.get(postorder[postRight]);
        root.left = helper(inorder, inLeft,  inRight - rightSize - 1, 
                           postorder, postRight - rightSize - 1, indexMap);
        root.right = helper(inorder,  inRight - rightSize + 1, inRight, 
                           postorder, postRight - 1, indexMap);
        return root;
    }
    
    // Time complexity: O(n).
    // Space complexity: O(n), when binary tree is highly unbalanced.
}