/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        ListNode cur = head;
        
        while (cur != null && cur.next != null) {
            // also need to check the boundary !!
            while (cur != null && cur.next != null && cur.next.val == cur.val) {
                ListNode next = cur.next;
                cur.next = null;
                cur = next;
            }
            p.next = cur;
            p = p.next;
            cur = cur.next;
        }
        return dummy.next;   
    }
    // Time = O(N)
    // Space = O(1)
}