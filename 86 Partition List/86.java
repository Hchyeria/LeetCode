/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummyFirst = new ListNode(0);
        ListNode firstTail = dummyFirst;
        ListNode dummySecond = new ListNode(0);
        ListNode secondTail = dummySecond;
        
        while (head != null) {
            if (head.val < x) {
                firstTail.next = head;
                firstTail = firstTail.next;
            } else {
                secondTail.next = head;
                secondTail = secondTail.next;
            }
            head = head.next;
        }
        // don't forget this !!!
        // if not, the list may have a cycle!
        secondTail.next = null;
        
        firstTail.next = dummySecond.next;
        return dummyFirst.next;
    }

    // Time = O(n)
    // Space = O(1)
}