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

    // Solution 1:
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return helper(root, new HashMap<>());
    }
    
    private int helper(TreeNode root, Map<TreeNode, Integer> map) {

        if (root == null) {
            return 0;
        }
        Integer count = map.get(root);
        if (count != null) {
            return count;
        }
        int val = 0;
        if (root.left != null) {
            val += helper(root.left.left, map) + helper(root.left.right, map);
        }
        if (root.right != null) {
            val += helper(root.right.left, map) + helper(root.right.right, map);   
        }
        val = Math.max(val + root.val, helper(root.left, map) + helper(root.right, map));
        map.put(root, val);
        return val;
    }

    // Time = O(6 ^ h)
    // Space = O(n)

    public int rob2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }
    
    // Solution 2:
    private int[] helper(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] l = helper(root.left);
        int[] r = helper(root.right);
        int[] res = new int[2];
        res[0] = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        res[1] = root.val + l[0] + r[0];
        
        return res;
    }

    // Time = O(2 ^ h)
    // Space = O(h)


    
}