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
    public List<TreeNode> generateTrees(int n) {
        
        List<TreeNode>[] dp = new List[n + 1];
        dp[0] = new ArrayList<>();
        if (n < 1) {
            return dp[n];
        }
        dp[0].add(null);
        dp[1] = new ArrayList<>();
        dp[1].add(new TreeNode(1));
        
        for (int i = 2; i <= n; ++i) {
            dp[i] = new ArrayList<>();
            for (int j = 1; j <= i; ++j) {
                List<TreeNode> l = dp[j - 1];
                List<TreeNode> r = dp[i - j];
                for (TreeNode left : l) {
                    for (TreeNode right : r) {
                        TreeNode root = new TreeNode(j);
                        root.left = left;
                        root.right = clone(right, j);
                        dp[i].add(root);
                    }
                }
            }
        }
        return dp[n];
    }
    
    private TreeNode clone(TreeNode root, int offset) {
        if (root == null) return root;
        TreeNode newRoot = new TreeNode(root.val + offset);
        newRoot.left = clone(root.left, offset);
        newRoot.right = clone(root.right, offset);
        return newRoot;
    }
    // Time = O(n ^ 2)
    // Space = O(n ^ 2)
}