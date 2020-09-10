/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        
        int i = n;
        while (fast != null && i-- > 0) {
            fast = fast.next;
        }
        if (fast == null) {
            head = head.next;
            return head;
        }
        
        while (fast.next != null) { // not i > 0 !!
            fast = fast.next;
            slow = slow.next;
        }
        ListNode nextNode = slow.next;
        slow.next = nextNode.next;
        nextNode.next = null;
        return head;
    }
    
    // Time = O(n)
    // Space = O(1)
}