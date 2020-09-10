/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        
        ListNode midNode = findMiddle(head);
        ListNode right = reverse(midNode.next);
        midNode.next = null;
        head = merge(head, right);
    }

    private ListNode findMiddle(ListNode head) {
        ListNode fast = head, slow = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode nextNode = head.next;
        ListNode newHead = reverse(nextNode);
        nextNode.next = head;
        head.next = null;
        
        return newHead;
    }
    
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while (head1 != null && head2 != null) {
            cur.next = head1;
            head1 = head1.next;
            cur = cur.next;
            cur.next = head2;
            head2 = head2.next;
            cur = cur.next;
        }
        
        cur.next = head1 == null ? head2 : head1;
        
        return dummy.next;
    }
}