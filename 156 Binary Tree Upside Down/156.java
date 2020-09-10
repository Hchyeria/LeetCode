class Solution {
    // Solution 1: recursion
    public TreeNode reverse(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newHead = reverse(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null; // avoid loop
        root.right = null;
        return newHead;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)

    // Solution 2: iterative
    public TreeNode reverse2(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode pre = null, right = null, temp = null;
        while (root != null) {
            TreeNode leftNode = root.left;
            temp = root.right;
            root.left = right;
            root.right = pre;
            pre = root;
            right = temp;
            root = leftNode;
        }
        return pre;
    }
    // Time complexity: O(n)
    // Space complexity: O(1)
}