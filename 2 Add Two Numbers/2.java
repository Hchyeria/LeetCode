/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        int add = 0, sum = 0;
        while (l1 != null || l2 != null) {
            int left = 0, right = 0;
            if (l1 != null) {
                left = l1.val;
            }
            if (l2 != null) {
                right = l2.val;
            }
            sum = left + right + add;
            add = sum / 10;
            p.next = new ListNode(sum % 10);
            p = p.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // postCheck!!
        // must check if the add == 1, we need to add more 1 in that case !!!!
        if (add == 1) {
            p.next = new ListNode(add);
        }
        return dummy.next;
    }
    // Time = O(m + n)
    // Space = O(1)
}