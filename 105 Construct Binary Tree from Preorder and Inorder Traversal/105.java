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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0
               || inorder == null || inorder.length == 0
               || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<>();
        int n = inorder.length;
        for (int i = 0; i < n; ++i) {
            indexMap.put(inorder[i], i);
        }
        
        return helper(preorder, 0, inorder, 0, n - 1, indexMap);
    }
    
    private TreeNode helper(int[] preorder, int preLeft, int[] inorder, 
                                int inLeft, int inRight, Map<Integer, Integer> indexMap) {
        if (inLeft > inRight) {
          return null;
        }
        TreeNode root = new TreeNode(preorder[preLeft]);
        int lSize = indexMap.get(preorder[preLeft]) - inLeft;
        root.left = helper(preorder, preLeft + 1, inorder, inLeft, inLeft + lSize - 1, indexMap);
        root.right = helper(preorder, preLeft + lSize + 1, inorder, inLeft + lSize + 1 , inRight, indexMap);
        return root;
    
    }

    // Time complexity: O(n).
    // Space complexity: O(n), when binary tree is highly unbalanced.
}