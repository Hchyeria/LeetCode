import java.util.Deque;

import javax.swing.tree.TreeNode;

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
    // Solution 1: recursion + binary search
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return helper(preorder, 0, preorder.length - 1);
    }
    
    private TreeNode helper(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[left]);
        int index = binarySearchFindLeftMost(preorder, preorder[left], left + 1, right);
        if (index == -1) {
            root.right = helper(preorder, left + 1, right);
        } else {
            root.left = helper(preorder, left + 1, index);
            root.right = helper(preorder, index + 1, right);
        }
        return root;
    }
    
    private int binarySearchFindLeftMost(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] < target 
                && (mid == array.length - 1 || (mid < array.length - 1 && array[mid + 1] > target))) {
                return mid;
            } else if (array[mid] < target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (array[right] < target) {
            return right;
        }
        if (array[left] < target) {
            return left;
        }
        // all larger than target
        return -1;
    }

    // Time Complexity: O(log(n) * height), O(log(n) * n) in worse case
    // Space Complexity: O(height), O(n) in worse case

    // Solution 2: iterative, linear scan + stack O(n)
    public TreeNode bstFromPreorder2(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode newHead = null;
        for (int val : preorder) {
            TreeNode node = new TreeNode(val);
            if (stack.isEmpty()) {
                newHead = node;
            } else {
                TreeNode p = null;
                while (!stack.isEmpty() && val > stack.peekFirst().val) {
                    p = stack.pollFirst();
                }
                if (p != null) {
                    p.right = node;
                } else {
                    stack.peekFirst().left = node;
                }

            }
            stack.offerFirst(node);
        }
        return newHead;
    }

    // Solution 3: recursion, linear scan + stack O(n)
    private int index = 0;
    public TreeNode bstFromPreorder3(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return helper2(preorder, Integer.MAX_VALUE);
    }

    private TreeNode helper2(int[] preorder, int max) {
        if (index >= preorder.length || preorder[index] > max) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[index++]);
        node.left = helper2(preorder, node.val);
        node.right = helper2(preorder, max);
        return node;
    }
}