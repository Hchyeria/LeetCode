
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // initial a dummy node , the dummy.next to represent the start of the sorted list
        // because during the sort, the head can not be definitely determined
        ListNode dummy = new ListNode(0);
        // the start of the node that prepare to be sorted
        ListNode cur = head;

        while (cur != null) {
            ListNode nextNode = cur.next;
           
            ListNode p = dummy;
            while (p.next != null && p.next.val < cur.val) {
                p = p.next;
            }

            cur.next = p.next;
            p.next = cur;
            cur = nextNode;
        }

        return dummy.next;

    }

}

/*
  Time complexity O(n^2)
  Space complexity O(1)
*/