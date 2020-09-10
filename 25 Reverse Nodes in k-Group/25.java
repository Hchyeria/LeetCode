/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int count = 0;
        ListNode cur = head;

        while (cur != null && count < k) {
            cur = cur.next;
            count++;
        }
        if (count == k) {
            ListNode last = reverseKGroup(cur, k);
            ListNode nextNode = null;
            while (count-- > 0) {
                nextNode = head.next;
                head.next = last;
                last = head;
                head = nextNode;
            }
            return last;
        }
        return head;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(log_k(n)), call stack
}