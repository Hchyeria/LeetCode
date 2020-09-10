/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        int count = 0;
        int k = 2;
        ListNode cur = head;
        while (cur != null && count < k) {
            cur = cur.next;
            count++;
        }
        if (count == k) {
            ListNode last = swapPairs(cur);
            ListNode nextNode = null;
            while (count-- > 0) {
                nextNode = head.next;
                head.next = last;
                last = head;
                head = nextNode;
            }
            head = last;
        }
        return head; 
    }
}