// LintCode 900. Closest Binary Search Tree Value

class Solution {
    // Solution 1: Recursion way
    public int closestValue(TreeNode root, int target) {
        if ((root.left == null && root.right == null) || root.val == target) {
            return root.val;
        }
        if (root.val < target) {
            if (root.right == null) {
                return root.val;
            } else {
                int right = closestValue(root.right, target);
                return Math.abs(root.val - target) <= Math.abs(right - target) ? root.val : right;
            }
        } else {
            if (root.left == null) {
                return root.val;
            } else {
                int left = closestValue(root.left, target);
                return Math.abs(root.val - target) <= Math.abs(left - target) ? root.val : left;
            }
        }

        // not fount
    }

    // Time Complexity: O(log(n))
    // Space Complexity: O(log(n))

    // Solution 2: iterative way
    public int closestValue2(TreeNode root, int target) {
        if ((root.left == null && root.right == null) || root.val == target) {
            return root.val;
        }
        int res = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(res - target)) {
                res = root.val;
            }
            if (root.val < target) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return res;
    }
    // Time Complexity: O(log(n))
    // Space Complexity: O(1)
}