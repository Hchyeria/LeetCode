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
        if (head == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(0);
        ListNode newHead = dummyNode;
        ListNode fast = head;
        while (fast != null) {
            ListNode cur = fast;
            int count = 0;
            while (fast != null && cur.val == fast.val) {
                fast = fast.next;
                count++;
            }
            if (count == 1) {
                newHead.next = cur;
                newHead = newHead.next;
            }
        }
        newHead.next = null; // very important 
        return dummyNode.next;
    }

    // Time complexity: O(n)
    // Space complexity: O(1)
}