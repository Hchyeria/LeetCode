/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode mid = findMid(head);
        mid = reverse(mid);
        while (head != null && mid != null) {
            if (head.val != mid.val) {
                return false;
            }
            head = head.next;
            mid = mid.next;
        }
        return true;
    }
    
    // return the second middle node of even length's LinkedList 
    private ListNode findMid(ListNode node) {
        ListNode fast = node, slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode nextNode = node.next;
        ListNode newHead = reverse(nextNode);
        nextNode.next = node;
        node.next = null;
        return newHead;
    }
    // Time = O(n)
    // Space = O(1)
}