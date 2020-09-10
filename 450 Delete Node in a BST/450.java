import java.util.TreeMap;

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
    public TreeNode deleteNode(TreeNode root, int key) {
        return delete1(root, key);
    }

    // recursive solution
    private TreeNode delete1(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        // find
        if (root.val > key) {
            root.left = delete1(root.left, key);
            return root;
        } else if (root.val < key) {
            root.right = delete1(root.right, key);
            return root;
        }

        // root != null && root.val == key
        return deleteRootNode(root);
    }

    // Time complexity is O(n), when the binary tree is highly unbalanced.
    // Space complexity is O(n), when the binary tree is highly unbalanced.


    private TreeNode deleteRootNode(TreeNode root) {
        if (root == null) {
            return null;
        }

        // case 1: have no left child or right child
        if (root.left == null) {
            // root = root.right; no!!!!
            return root.right;
        } else if (root.right == null) {
            // root = root.left;
            return root.left;
        }

        // case 2: have both left and right child
        // we choose the smallest node of right subtree to replace root
        // case 2.1: right subtree have no left child
        if (root.right.left == null) {
            root.right.left = root.left;
            return root.right;
        }

        // case 2.2:
        TreeNode swapNode = deleteSmallest(root.right);
        swapNode.left = root.left;
        swapNode.right = root.right;
        return swapNode;
    }

    // given a TreeNode cur, find and delete the smallest node
    // from its right sub-tree
    private TreeNode deleteSmallest(TreeNode root) {
        TreeNode pre = null;
        while (root.left != null) {
            pre = root;
            root = root.left;
        }

        // root.left == null
        pre.left = root.right;
        return root;
    }

    // iterative solution
    private TreeNode delete2(TreeNode root, int key) {
        TreeNode cur = root, pre = null;
        while (cur != null && cur.val != key) {
            pre = cur;
            if (cur.val < key) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        // cur == null || cur.cal == key
        // cur is the root
        if (pre == null) {
            return deleteRootNode(cur);
        }

        if (pre.left == cur) {
            pre.left = deleteRootNode(cur);
        } else {
            pre.right = deleteRootNode(cur);
        }
        return root;
    }
    
    // Time complexity is O(n), when the binary tree is highly unbalanced.
    // Space complexity is O(1).


}