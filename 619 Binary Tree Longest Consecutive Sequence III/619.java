// LintCode

/**
 * Definition for a multi tree node.
 * public class MultiTreeNode {
 *     int val;
 *     List<TreeNode> children;
 *     MultiTreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int longestConsecutive3(MultiTreeNode root) {
        // Write your code here
        if (root == null) {
            return 0;
        }
        int[] globalMax = new int[1];
        helper(globalMax, root);
        return globalMax[0];
    }

    // return [inc, dec] 
    private int[] helper(int[] res, MultiTreeNode node) {
        if (node == null) {
            return new int[] {0, 0};
        }

        int dec = 1, inc = 1;
        for (MultiTreeNode child : node.children) {
            if (child != null) {
                int[] childRes = helper(res, child);
                if (node.val < child.val) {
                    inc = Math.max(inc, childRes[0] + 1);
                } else if (node.val > child.val) {
                    dec = Math.max(Dec, childRes[1] + 1);
                }
            }
        }
        res[0] = Math.max(res[0], dec + inc - 1);
        return new int[] {inc, dec};
    }

}