/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode[] nodes = findMid(head);
        ListNode midNode = nodes[0];
        ListNode rootNode = nodes[1];
        TreeNode root = new TreeNode(rootNode.val);
        midNode.next = null;
        root.left = sortedListToBST(head);
        ListNode rightNode = rootNode.next;
        rootNode.next = null;
        root.right = sortedListToBST(rightNode);
        return root;
    }
    
    private ListNode[] findMid(ListNode head) {
        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return new ListNode[] {pre, slow};
    }
    // Time = O(n * log(n))
    // Space = O(log(n))
}