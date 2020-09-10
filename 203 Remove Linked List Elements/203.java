/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        
        while (head != null) {
            if (head.val != val) {
                p.next = head;
                p = p.next;
            }
            head = head.next;
        }
        // remember to set null
        p.next = null;
        return dummy.next;
    }
    // Time = O(n)
    // Space = O(1)
}