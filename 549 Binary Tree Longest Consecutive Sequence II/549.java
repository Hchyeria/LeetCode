// LintCode 614. Binary Tree Longest Consecutive Sequence II

class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive2(TreeNode root) {
        // write your code here
        if (root == null ){
            return 0;
        }
        int[] res = new int[1];
        dfs(root, res);
        return res[0];
    }
    
    private int[] dfs(TreeNode node, int[] res) {
        if (node == null) {
            return new int[] {0, 0};
        }
        int dec = 1, inc = 1;
        if (node.left != null) {
            int[] left = dfs(node.left, res);
            if (node.val == node.left.val - 1) {
                inc = left[1] + 1;
            } else if (node.val - 1 == node.left.val) {
                dec = left[0] + 1;
            }
        }
        if (node.right != null) {
            int[] right = dfs(node.right, res);
            if (node.val == node.right.val - 1) {
                inc = Math.max(inc, right[1] + 1);
            } else if (node.val - 1 == node.right.val) {
                dec = Math.max(dec, right[0] + 1);
            }
        }
        res[0] = Math.max(res[0], inc + dec - 1);
        return new int[] { dec, inc };
    }
    // Time = O(n);
    // Space = O(height)
}