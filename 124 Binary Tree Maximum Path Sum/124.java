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
    public int maxPathSum(TreeNode root) {
        if (root == null) {
                return 0;
        }
        int[] globalMax = new int[1];
        globalMax[0] = Integer.MIN_VALUE; // can not be initialized as 0
        dfs(root, globalMax);
        return globalMax[0];
    }

    private int dfs(TreeNode root, int[] globalMax) {
        // Base case
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, globalMax);
        int right = dfs(root.right, globalMax);
        // if sum of path less than 0, we don't need to pass the path
        left = left < 0 ? 0 : left;
        right = right < 0 ? 0 : right;
        globalMax[0] = Math.max(globalMax[0], left + right + root.val);
        return Math.max(Math.max(left, right), 0) + root.val;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(height)

}