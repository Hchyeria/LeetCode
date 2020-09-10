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
    
    // Solution 1: recursion
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        int n = nums.length;
        return helper(nums, 0, n - 1);
    }
    
    private TreeNode helper(int[] array, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(array[mid]);
        root.left = helper(array, left, mid - 1);
        root.right = helper(array, mid + 1, right);
        return root;
    }
    // Time = O(n)
    // Space = O(log(n))


    // Solution 2: iterative
    private static class MyNode {
        int l;
        int r;
        TreeNode node;
        
        MyNode(int l, int r, TreeNode node) {
            this.l = l;
            this.r = r;
            this.node = node;
        }
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        
        int n = nums.length;
        int left = 0, right = n - 1;
        Deque<MyNode> stack = new LinkedList<>();
        TreeNode root = new TreeNode(0);
        stack.offerFirst(new MyNode(left, right, root));
        
        while (!stack.isEmpty()) {
            MyNode node = stack.pollFirst();
            int mid = node.l + (node.r - node.l) / 2;
            node.node.val = nums[mid];

            if (node.l < mid) {
                TreeNode l = new TreeNode(0);
                stack.offerFirst(new MyNode(node.l, mid - 1,l));
                node.node.left = l;
            }
            
            if (node.r > mid) {
                TreeNode r = new TreeNode(0);
                stack.offerFirst(new MyNode(mid + 1, node.r, r));
                node.node.right = r;
            }
        }
        
        return root;
    }
}