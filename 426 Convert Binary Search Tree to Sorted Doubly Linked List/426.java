// LintCode 1534. Convert Binary Search Tree to Sorted Doubly Linked List

/* 
    Convert a BST to a sorted circular doubly-linked list in-place. 
    Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
    Let's take the following BST as an example, it may help you understand the problem better:

    We want to transform this BST into a circular doubly linked list. 
    Each node in a doubly linked list has a predecessor and successor. 
    For a circular doubly linked list, the predecessor of the first element is the last element, 
    and the successor of the last element is the first element.

    The figure below shows the circular doubly linked list for the BST above. 
    The "head" symbol means the node it points to is the smallest element of the linked list.

    Specifically, we want to do the transformation in place. After the transformation, 
    the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

    The figure below shows the transformed BST. The solid line indicates the successor relationship, 
    while the dashed line means the predecessor relationship. 
*/

class Solution {

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode[] newHead = new TreeNode[1];
        TreeNode[] prev = new TreeNode[1];
        helper(root, prev, newHead);
        prev[0].right = newHead[0];
        newHead[0].left = prev[0];
        return newHead[0];
    }

    // Solution 1: recursion
    private void helper(TreeNode node, TreeNode[] prev, TreeNode[] newHead) {
        if (node == null) {
            return;
        }
        helper(node.left, prev, newHead);
        TreeNode newTreeNode = new TreeNode(node.val);
        if (prev[0] == null) {
            newHead[0] = newTreeNode;
        } else {
            prev[0].right = newTreeNode;
            newTreeNode.left = prev[0];
        }
        prev[0] = newTreeNode;
        helper(node.right, prev, newHead);

    }
    // Time Complexity: O(n), in-order traversal
    // Space Complexity: O(height)

    // Solution 2: iterative
    public TreeNode treeToDoublyList2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newHead = null;
        TreeNode pre = null;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.offerFirst(p);
                p = p.left;
            } else {
                TreeNode node = stack.pollFirst();
                if (pre == null) {
                    newHead = node;
                } else {
                    pre.right = node;
                    node.left = pre;
                }

                pre = node;
                p = node.right;
            }
        }
        pre.right = newHead;
        newHead.left = pre;
        return newHead;
    }

    // Time Complexity: O(n), in-order traversal
    // Space Complexity: O(n), no stack overflow
}