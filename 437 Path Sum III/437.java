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
    public int pathSum(TreeNode root, int sum) {
        int[] count = {0};
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> prefixSum = new HashMap<>();
        helper(root, sum, 0, prefixSum, count);
        return count[0];
        
    }
    
    private void helper(TreeNode root, int target, int sum, Map<Integer, Integer> prefixSum, int[] count) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (sum == target) {
            count[0]++;
        }
        if (prefixSum.containsKey(sum - target)) {
            count[0] += prefixSum.get(sum - target);
        }
        prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1);
        helper(root.left, target, sum, prefixSum, count);
        helper(root.right, target, sum, prefixSum, count);
        prefixSum.put(sum, prefixSum.get(sum) - 1);
    }

    // Time Complexity: O(0)
    // Space Complexity: O(height)
}