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
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        
        Set<Integer> visited = new HashSet<>();
        
        return helper(root, visited, k);
    }
    
    private boolean helper(TreeNode root, Set<Integer> visited, int k) {
        if (root == null) return false;;
        if (visited.contains(k - root.val)) {
            return true;
        }
        visited.add(root.val);
        
        return helper(root.left, visited, k) || helper(root.right, visited, k);
    }

    // Time = O(N)
    // Space = O(height)
}