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
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> indexArray = new ArrayList<>();
        int[] max = new int[1];
        dfs(root, 1, 0, indexArray, max);
        return max[0];
    }
    
    private void dfs(TreeNode node, int index, int level, List<Integer> indexArray, int[] max) {
        if (node == null) {
            return;
        }
        if (indexArray.size() == level) {
            indexArray.add(index);
        }
        max[0] = Math.max(max[0], index - indexArray.get(level) + 1);
        dfs(node.left, index * 2 + 1, level + 1, indexArray, max);
        dfs(node.right, index * 2 + 2, level + 1, indexArray, max);
    }
    
    // Time = O(n)
    // Space = O(height)
}